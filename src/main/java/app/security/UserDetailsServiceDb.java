package app.security;


import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class UserDetailsServiceDb implements UserDetailsService {

    private final DbUserRepository dbUserRepository;
    private final CustomerRepository customerRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/1").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository
                .findByNameIgnoreCase(username)
                .map(this::remap)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username %s not found", username)
                ));
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return dbUserRepository
//                .findByUsername(username)
//                .map(this::remap)
//                .orElseThrow(() -> new UsernameNotFoundException(
//                        String.format("Username %s not found", username)
//                ));
//    }
//
//        private UserDetails remap(DbUser user) {
//        return User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .build();
//    }
    private UserDetails remap(Customer user) {
        return User
                .withUsername(user.getName())
                .password(user.getPassword())
                .build();
    }


}
