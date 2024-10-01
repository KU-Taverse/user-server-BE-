package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.Item;
import ku.user.domain.inventory.domain.ItemType;
import lombok.Builder;

import java.util.List;

/**
 * 아이템 추가 API Response
 * @param id
 * @param userId
 * @param itemList
 * 아이템을 추가하지만 인밴토리를 반환한다.
 */
@Builder
public record PostItemResponse(Long id,
                                   Long userId,
                                   List<GetItemResponse> itemList) {

    public static PostItemResponse toDto(Inventory inventory) {

        List<GetItemResponse> getItemResponseList = inventory.getItemList().stream().map(GetItemResponse::toDto).toList();

        return PostItemResponse.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .itemList(getItemResponseList)
                .build();

    }
}