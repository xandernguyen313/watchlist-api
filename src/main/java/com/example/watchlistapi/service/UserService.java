package com.example.watchlistapi.service;


import com.example.watchlistapi.exception.InformationExistException;
import com.example.watchlistapi.model.User;
import com.example.watchlistapi.model.request.LoginRequest;
import com.example.watchlistapi.model.response.LoginResponse;
import com.example.watchlistapi.repository.UserRepository;
import com.example.watchlistapi.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User userObject){
        if(!userRepository.existsByEmailAddressIgnoreCase(userObject.getEmailAddress())){
            //if the user does not exist, we have to create the user and user profile
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("a user account for " + userObject.getEmailAddress() + " already exists");
        }
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddressIgnoreCase(email);
    }

    // Authenticate user and generate a token
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
