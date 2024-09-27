package ku.user.domain.user.domain;

import ku.user.domain.user.infrastructure.entity.UserEntity;
import lombok.Builder;

import java.time.LocalDateTime;

public class CreateUser {

    private String email;
    private String nickname;
    private String password;

    @Builder
    public CreateUser(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .lastLoginAt(LocalDateTime.now())
                .build();
    }
}
