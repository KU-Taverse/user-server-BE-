package ku.user.domain.character.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private CharacterType characterType;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Builder.Default
    private int currentMoney = 0;

    public Character update(Character character) {
        this.nickname = character.getNickname();
        this.characterType = character.getCharacterType();
        this.currentMoney = character.getCurrentMoney();
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void pay(int price) {
        this.currentMoney -= price;
    }
}
