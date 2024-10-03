package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Item;
import ku.user.domain.inventory.domain.ItemType;
import lombok.Builder;

@Builder
public record GetItemResponse(Long id,
                              ItemType itemType,
                              Long itemNumber) {
    public static GetItemResponse toDto(Item item) {
        return GetItemResponse.builder()
                .id(item.getId())
                .itemType(item.getItemType())
                .itemNumber(item.getItemType().getItemNumber())
                .build();

    }
}
