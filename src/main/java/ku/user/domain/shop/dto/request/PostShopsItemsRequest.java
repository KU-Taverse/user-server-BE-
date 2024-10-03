package ku.user.domain.shop.dto.request;

import ku.user.domain.shop.domain.ShopItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostShopsItemsRequest {

    private String name;
    private String description;
    private int price;
    private int count;

    public static ShopItem toEntity(PostShopsItemsRequest postShopsItemsRequest) {
        return ShopItem.builder()
                .name(postShopsItemsRequest.getName())
                .description(postShopsItemsRequest.getDescription())
                .price(postShopsItemsRequest.getPrice())
                .count(postShopsItemsRequest.getCount())
                .build();
    }
}
