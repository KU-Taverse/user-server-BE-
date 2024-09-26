package ku.user.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ku.user.user.domain.CreateUser;
import ku.user.user.infrastructure.entity.UserEntity;
import ku.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public String loginView() {
        return "/login2";
    }

    @PostMapping("/sign")
    public ResponseEntity<UserEntity> sign(@RequestBody CreateUser createUser){
        UserEntity userEntity = userService.create(createUser);
        log.info(userEntity.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @GetMapping("/success")
    public ResponseEntity<String> success(HttpServletRequest request,HttpServletResponse response) {
        log.info((String) request.getSession().getAttribute("token"));
        response.setHeader("token", (String) request.getSession().getAttribute("token"));
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("test");
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@RequestBody Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
}
