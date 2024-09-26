package ku.user.user.domain;

import ku.user.user.infrastructure.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private Date createdAt;
    private String password;

    @Builder
    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserDto from(UserEntity userEntity) {
        return UserDto.builder()
                .name(userEntity.getNickname())
                .email(userEntity.getEmail())
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .nickname(name)
                .email(email)
                .password(password)
                .build();
    }

}
