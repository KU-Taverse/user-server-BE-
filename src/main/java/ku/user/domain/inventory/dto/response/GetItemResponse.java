package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.InventoryItem;
import lombok.Builder;

@Builder
//TODO
public record GetItemResponse(Long id,
                              String itemName,
                              Long itemNumber) {
    public static GetItemResponse toDto(InventoryItem item) {
        return GetItemResponse.builder()
                .id(item.getId())
                //.itemName(item.getItemType().getItemName())
                //.itemNumber(item.getItemType().getItemNumber())
                .build();

    }
}
