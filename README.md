# X-Finance Backend
Project Description: This backend application allows users to store their watchlists and stocks.

## Entity Relationship Diagram
![ERD](https://github.com/xandernguyen313/watchlist-api/blob/main/erd.JPG)

## Endpoints:

### URL: 
*https://watch-list-api.herokuapp.com/*

| Request Type | URL                       | Request Body               | Request Header              | Action                                                          | Access  |   
|--------------|---------------------------|----------------------------|-----------------------------|-----------------------------------------------------------------|---------|
| POST         | /auth/users/register      | emailAddress <br> password | Authorization: none         | registers a user                                                | PUBLIC  |
| POST         | /auth/users/login         | email <br> password        | Authorization: none         | allows a user to login                                          | PUBLIC  |
| GET          | /api/watchlists           |                            | Authorization: Bearer token | returns a list of all watchlists belongs to user                | PRIVATE |
| POST         | /api/watchlists           | name                       | Authorization: Bearer token | adds a watchlist to the database                                | PRIVATE |
| GET          | /api/watchlists/{list-id} |                            | Authorization: Bearer token | returns a single watchlist                                      | PRIVATE |
| PUT          | /api/watchlists/{list-id} | name                       | Authorization: Bearer token | updates an existing watchlist name                              | PRIVATE |
| DELETE       | /api/watchlists/{list-id} |                            | Authorization: Bearer token | removes a watchlist from the database                           | PRIVATE |
| POST  | /api/watchlists/{list-id}/symbols | ticker, companyName       | Authorization: Bearer token | adds a symbol to the watchlist                                  | PRIVATE |
| GET          | /api/watchlists/{list-id}/symbols/{symbol-id}|         | Authorization: Bearer token | returns a single symbol                                         | PRIVATE | 
| GET          | /api/watchlists/{list-id}/symbols|                     | Authorization: Bearer token | returns all symbols belongs to the watchlist with that id       | PRIVATE | 
| DELETE       | /api/watchlists/{list-id}/symbols/{symbol-id}|         | Authorization: Bearer token |  removes a symbol from the database                             | PRIVATE |

## Technology Used:
- SpringBoot (framework)
- Maven (dependency management)
- Heroku
- Postman (endpoint testing)
- IntelliJ IDEA (IDE)
- pgAdmin (managing the database)

## Major Challenges:
### Hurdle 1:
Issue: <br /> <br />
```
Access to XMLHttpRequest at 'http://localhost:9092/auth/users/register'
from origin 'http://localhost:4200' has been blocked by CORS policy
```
Solution:<br /> <br />
```
@Configuration
public class ConsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("*");
            }
        };
    }
}
```
```
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      // only allowed urls without JWT
      http.cors().and()
              .authorizeRequests().antMatchers(
                      "/auth/users", "/auth/users/register","/auth/users/login").permitAll()
              .anyRequest().authenticated()
              .and().sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and().csrf().disable();
      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
```
