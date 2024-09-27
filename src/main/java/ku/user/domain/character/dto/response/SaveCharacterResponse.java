package ku.user.domain.character.dto.response;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import lombok.Builder;

@Builder
public record SaveCharacterResponse(Long id, String nickname,
                                    CharacterType characterType, Long userId,
                                    int currentMoney) {

    public static SaveCharacterResponse toDto(Character character){
        return SaveCharacterResponse.builder()
                .id(character.getId())
                .nickname(character.getNickname())
                .characterType(character.getCharacterType())
                .userId(character.getUserId())
                .currentMoney(character.getCurrentMoney())
                .build();
    }
}
