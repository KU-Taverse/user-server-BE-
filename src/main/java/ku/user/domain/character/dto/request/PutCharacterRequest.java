package ku.user.domain.character.dto.request;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PutCharacterRequest {
    private String email;
    private String nickname;
    private CharacterType characterType;

    public static Character toEntity(PutCharacterRequest putCharacterRequest){
        return Character.builder()
                .nickname(putCharacterRequest.getNickname())
                .characterType(putCharacterRequest.getCharacterType())
                .build();

    }
}
