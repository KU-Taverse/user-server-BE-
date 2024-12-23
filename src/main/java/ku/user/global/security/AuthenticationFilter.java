package ku.user.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ku.user.domain.user.infrastructure.entity.UserStatus;
import ku.user.global.response.ApiResponse;
import ku.user.domain.user.domain.RequestLogin;
import ku.user.domain.user.domain.UserDto;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import ku.user.domain.user.infrastructure.repository.UserRepository;
import ku.user.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private UserRepository userRepository;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.userRepository = userRepository;
        this.env = env;
    }



    // 요청 정보를 보냈을 때 처리하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try{
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(),RequestLogin.class);
            request.setAttribute("requestLogin", creds);
            // 입력 받은 이메일과 비밀번호를 spring security에서 사용할 수 있는 형태로 변경해줘야함.
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // 실제 인증처리가 성공했을 때 어떤 처리를 해줄 것인지(토큰을 만든다거나 반환값을 뭘 줄 것인가)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String userName = (((User)authResult.getPrincipal()).getUsername());
        UserDto userDetails = userService.getUserDetailsByEmail(userName);
        UserEntity userEntity = userRepository.findByEmail(userName).get();
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse<String> apiResponse;

        response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정


        if (userEntity.getPassword().getFailedCount() > 5) {
            // 비밀번호 재설정이 필요한 경우에 대한 처리
            apiResponse = new ApiResponse<>(false, "비밀번호재설정", null);

            String jsonResponse = objectMapper.writeValueAsString(apiResponse);
            response.getWriter().write(jsonResponse);
            return;
        }
        if(userEntity.getPassword().isExpiration()){
            apiResponse = new ApiResponse<>(false, "만료되었음", null);

            String jsonResponse = objectMapper.writeValueAsString(apiResponse);
            response.getWriter().write(jsonResponse);
            return;
        }

        userEntity.getPassword().updateFailedCount(true);
        userRepository.save(userEntity);

        String token = Jwts.builder()
                .setSubject(userDetails.getEmail())
                .claim("status", userEntity.getStatus().name())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,env.getProperty("token.secret"))
                .compact();


        boolean isAdmin = UserStatus.ADMIN.equals(userEntity.getStatus());

        response.addHeader("token",token);
        response.addHeader("email",userDetails.getEmail());
        if(isAdmin) response.addHeader("admin", String.valueOf(isAdmin));
        // 여기에 관리자 로그인 확인?
        apiResponse = new ApiResponse<>(true, "로그인 성공", null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);

    }
}

