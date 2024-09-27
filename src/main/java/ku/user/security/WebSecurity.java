package ku.user.security;

import ku.user.user.infrastructure.repository.UserRepository;
import ku.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final UserService userService;
    private final UserRepository userRepository;
    private final Environment env;

    public WebSecurity(UserService userService, UserRepository userRepository, Environment env) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.env = env;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .formLogin(form -> form.disable())
//                .formLogin(form -> form
//                        .disable()
////                        .failureHandler(new CustomLoginFailureHandler(userService,userRepository))
//                )
                .addFilter(getAuthenticationFilter(authManager(http.getSharedObject(AuthenticationConfiguration.class))));

        return http.build();
    }

    public AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,userService,userRepository,env);
        authenticationFilter.setAuthenticationFailureHandler(new CustomLoginFailureHandler(userService,userRepository));
        return  authenticationFilter;
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // Directly get the AuthenticationManager
    }


}
