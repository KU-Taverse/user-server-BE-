package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.Item;
import lombok.Builder;

import java.util.List;

@Builder
public record GetInventoryResponse(Long id,
                                   Long userId,
                                   List<GetItemResponse> itemList) {

    public static GetInventoryResponse toDto(Inventory inventory) {

        List<GetItemResponse> getItemResponseList = inventory.getItemList().stream().map(GetItemResponse::toDto).toList();

        return GetInventoryResponse.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .itemList(getItemResponseList)
                .build();

    }
}


