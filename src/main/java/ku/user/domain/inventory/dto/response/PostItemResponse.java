package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Inventory;
import lombok.Builder;

import java.util.List;

/**
 * 아이템 추가 API Response
 * @param id
 * @param characterId
 * @param itemList
 * 아이템을 추가하지만 인밴토리를 반환한다.
 */
@Builder
//TODO 현재 사용되지 않지만 삭제하지 않음
public record PostItemResponse(Long id,
                                   Long characterId,
                                   List<GetItemResponse> itemList) {

    public static PostItemResponse toDto(Inventory inventory) {

        return PostItemResponse.builder()
                .id(inventory.getId())
                .characterId(inventory.getCharacterId())
                .build();

    }
}