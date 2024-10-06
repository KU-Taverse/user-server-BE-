package ku.user.domain.inventory.dto.response;

import ku.user.domain.inventory.domain.Inventory;
import lombok.Builder;

import java.util.List;

@Builder
public record GetInventoryResponse(Long id,
                                   Long characterId,
                                   List<GetItemResponse> itemList) {

    public static GetInventoryResponse toDto(Inventory inventory) {

        List<GetItemResponse> getItemResponseList = inventory.getItemList().stream().map(GetItemResponse::toDto).toList();

        return GetInventoryResponse.builder()
                .id(inventory.getId())
                .characterId(inventory.getCharacterId())
                .itemList(getItemResponseList)
                .build();

    }
}


