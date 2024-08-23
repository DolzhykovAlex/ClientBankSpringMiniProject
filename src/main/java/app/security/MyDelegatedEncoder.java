   package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Configuration
public class MyDelegatedEncoder {

  private final static String ALGORITHM = "bcrypt";

  @Bean
  public static PasswordEncoder build() {
    return new DelegatingPasswordEncoder(ALGORITHM,
        new HashMap<String, PasswordEncoder>(11) {{
          put("bcrypt", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());

        }}
    );
  }
}
