package app.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class DbUser {

    private final static String DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;


    public DbUser(String username, String password, String... roles) {
        this.username = username;
        this.password = password;

    }


}
