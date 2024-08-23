package app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersController {
    private final  DbUserRepository dbUserRepository;

//
//    @GetMapping
//    public String book() {
//        return "Java";
//    }
//
//    @GetMapping("2")
//    public List<DbUser> all() {
//        return dbUserRepository.findAll();
//    }

}
