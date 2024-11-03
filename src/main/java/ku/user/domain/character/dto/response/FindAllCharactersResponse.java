package ku.user.domain.character.dto.response;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.domain.CharacterType;
import ku.user.domain.inventory.domain.Inventory;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllCharactersResponse (String nickname,
                                         CharacterType characterType, Long userId,
                                         int currentMoney,
                                         List<Long> useItemList){
    public static FindAllCharactersResponse toDto(Inventory inventory, Character character){
        return FindAllCharactersResponse.builder()
                .nickname(character.getNickname())
                .userId(character.getUserId())
                .characterType(character.getCharacterType())
                .currentMoney(character.getCurrentMoney())
                .useItemList(List.of(inventory.getAuraIndex(),
                        inventory.getTitleColorIndex(),
                        inventory.getTitleBackgroundIndex()))
                .build();
    }
}
