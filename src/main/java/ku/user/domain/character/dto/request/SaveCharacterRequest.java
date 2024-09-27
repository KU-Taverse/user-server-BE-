package ku.user.domain.character.dto.request;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaveCharacterRequest {

    private String email;
    private String nickname;
    private CharacterType characterType;

    public static Character toEntity(SaveCharacterRequest saveCharacterRequest){
        return Character.builder()
                .nickname(saveCharacterRequest.getNickname())
                .characterType(saveCharacterRequest.getCharacterType())
                .build();

    }
}
