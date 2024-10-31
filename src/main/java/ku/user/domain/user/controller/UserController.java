package ku.user.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ku.user.domain.user.domain.CreateUser;
import ku.user.domain.user.domain.GetUsersResponse;
import ku.user.domain.user.service.UserService;
import ku.user.global.response.ApiResponse;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign")
    public ApiResponse<String> sign(@RequestBody CreateUser createUser){
        UserEntity userEntity = userService.create(createUser);
        log.info(userEntity.toString());
        return new ApiResponse<>(true,"가입 성공하였습니다.",null);
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


    // 유저 정보 가져오기
    @GetMapping("/users")
    public List<GetUsersResponse> getUsers(){
        List<UserEntity> userEntities = userService.getUsers();
        List<GetUsersResponse> userResponses = userEntities.stream()
                .map(GetUsersResponse::fromEntity) // 정적 메서드 사용
                .toList();

        return userResponses;
    }

    // 밴하기
    @PostMapping("/user/ban/{userId}")
    public void banUser(@PathVariable Long userId){
        userService.banUser(userId);
    }

}
