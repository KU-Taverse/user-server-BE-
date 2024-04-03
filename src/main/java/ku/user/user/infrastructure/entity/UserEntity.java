package ku.user.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
        this.password = new Password(password, Clock.systemDefaultZone());
        this.status = UserStatus.GENERAL;
    }
}
