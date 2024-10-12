package ku.user.domain.character.dto.response;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import ku.user.domain.inventory.domain.Inventory;
import lombok.Builder;

import java.util.List;

@Builder
public record FindCharacterByEmailResponse (Long id, String nickname,
                                           CharacterType characterType, Long userId,
                                           int currentMoney,
                                            List<Long> useItemList) {

    public static FindCharacterByEmailResponse toDto(Inventory inventory, Character character){
        return FindCharacterByEmailResponse.builder()
                .id(character.getId())
                .nickname(character.getNickname())
                .characterType(character.getCharacterType())
                .userId(character.getUserId())
                .currentMoney(character.getCurrentMoney())
                .useItemList(List.of(inventory.getAuraIndex(),
                        inventory.getTitleColorIndex(),
                        inventory.getTitleBackgroundIndex()))
                .build();
    }
}