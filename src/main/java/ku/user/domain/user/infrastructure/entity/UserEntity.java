package ku.user.domain.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;

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

    @Column(name = "accountStatus")
    @Enumerated(EnumType.STRING)
    private UserAccountStatus accountStatus;

    @Column(name = "last_login_at")
    LocalDateTime lastLoginAt;

    @Builder
    public UserEntity(String email, String nickname, String password, Long id, LocalDateTime lastLoginAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.lastLoginAt = lastLoginAt;
        this.password = password != null ? new Password(password, Clock.systemDefaultZone()) : new Password("",Clock.systemDefaultZone()); // 빈 문자열로 초기화
        this.status = UserStatus.GENERAL;
        this.accountStatus = UserAccountStatus.RUN;
    }

    public static UserEntity createInstance() {
        return new UserEntity();
    }
}
