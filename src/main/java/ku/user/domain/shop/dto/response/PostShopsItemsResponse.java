package ku.user.domain.shop.dto.response;

import ku.user.domain.shop.domain.ShopItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record PostShopsItemsResponse(Long id,
                                     String name,
                                     String description,
                                     int price,
                                     int count) {

    public static PostShopsItemsResponse toDto(ShopItem shopItem) {
        return PostShopsItemsResponse.builder()
                .id(shopItem.getId())
                .name(shopItem.getName())
                .description(shopItem.getDescription())
                .price(shopItem.getPrice())
                .count(shopItem.getCount())
                .build();
    }
}
