package ku.user.domain.shop.dto.response;

import ku.user.domain.shop.domain.Item;
import lombok.Builder;

@Builder
public record GetShopItemResponse(Long id,
                                  String name,
                                  String description,
                                  int price,
                                  int count) {

    public static GetShopItemResponse toDto(Item shopItem) {
        return GetShopItemResponse.builder()
                .id(shopItem.getId())
                .name(shopItem.getName())
                .description(shopItem.getDescription())
                .price(shopItem.getPrice())
                .count(shopItem.getCount())
                .build();
    }

}
