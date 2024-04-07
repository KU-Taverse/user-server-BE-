package ku.user.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "nickname")
    private String nickname;

    @Embedded
    private Password password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_login_at")
    private Long lastLoginAt;

    @Builder
    public UserEntity(String email, String nickname, String password, Long id) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password != null ? new Password(password, Clock.systemDefaultZone()) : new Password("",Clock.systemDefaultZone()); // 빈 문자열로 초기화
        this.status = UserStatus.GENERAL;
    }
}
