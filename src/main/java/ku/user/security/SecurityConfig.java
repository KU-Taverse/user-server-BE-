//package ku.user.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final InMemoryClientRegistrationRepository clientRegistrationRepository;
//    private final AuthenticationSuccessHandler OAuth2LoginSuccessHandler;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(CsrfConfigurer::disable)
//                .authorizeHttpRequests(o->o
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .anyRequest().permitAll()
//                )
//                .headers(o->o.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .oauth2Login(o -> o
//                        .loginPage("/login")
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService) // 여기서 사용하는 서비스가 유저 정보를 불러올 서비스
//                    )
//                        .successHandler(OAuth2LoginSuccessHandler)
//                .clientRegistrationRepository(clientRegistrationRepository)
//        );
//        return http.build();
//    }
//}
