package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
class AppClient {
    public static void main(String[] args) {
        SpringApplication.run(AppClient.class, args);
    }
}
