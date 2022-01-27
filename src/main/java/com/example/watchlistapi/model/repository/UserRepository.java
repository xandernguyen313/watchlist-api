package com.example.watchlistapi.model.repository;


import com.example.watchlistapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAddressIgnoreCase(String userEmailAddress);

    User findUserByEmailAddressIgnoreCase(String userEmailAddress);
}
