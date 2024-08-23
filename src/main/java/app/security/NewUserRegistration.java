package app.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewUserRegistration {

    private final DbUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void makeUsers() {
        repository.saveAll(List.of(
                new DbUser("jim", encode("password1")),
                new DbUser("jack", encode("password2")),
                new DbUser("alex", encode("password3")),
                new DbUser("serg", encode("password4")),
                new DbUser("mako", encode("password5"))
        ));
    }
}
