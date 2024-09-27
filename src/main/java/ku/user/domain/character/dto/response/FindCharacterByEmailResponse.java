package ku.user.domain.character.dto.response;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import lombok.Builder;

@Builder
public record FindCharacterByEmailResponse (Long id, String nickname,
                                           CharacterType characterType, Long userId,
                                           int currentMoney) {

    public static FindCharacterByEmailResponse toDto(Character character){
        return FindCharacterByEmailResponse.builder()
                .id(character.getId())
                .nickname(character.getNickname())
                .characterType(character.getCharacterType())
                .userId(character.getUserId())
                .currentMoney(character.getCurrentMoney())
                .build();
    }
}