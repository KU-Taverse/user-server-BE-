package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.InventoryItem;
import lombok.Builder;

import java.util.List;

@Builder
//TODO
public record GetInventoryResponse(List<Long> itemList,
                                   List<Integer> useItemList) {

    public static GetInventoryResponse toDto(Inventory inventory, List<InventoryItem> inventoryItemList) {

        //List<GetItemResponse> getItemResponseList = inventory.getItemList().stream().map(GetItemResponse::toDto).toList();

        return GetInventoryResponse.builder()
                .itemList(inventoryItemList.stream().map(data->data.getItem().getId()).toList())
                .useItemList(List.of(inventory.getAuraIndex(),
                        inventory.getTitleColorIndex(),
                        inventory.getTitleBackgroundIndex()))
                .build();

    }
}


