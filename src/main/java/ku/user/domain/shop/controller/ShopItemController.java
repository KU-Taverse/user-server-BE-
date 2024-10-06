package ku.user.domain.shop.controller;

import ku.user.domain.shop.domain.Item;
import ku.user.domain.shop.dto.request.PostShopsItemsRequest;
import ku.user.domain.shop.dto.response.GetShopItemResponse;
import ku.user.domain.shop.dto.response.PostShopsItemsResponse;
import ku.user.domain.shop.service.ShopItemService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopItemController {

    private final ShopItemService shopItemService;

    @GetMapping("/shops/items")
    public ApiResponse<List<GetShopItemResponse>> getShopList() {
        List<Item> shopItemList = shopItemService.findAll();
        return new ApiResponse<>(true, shopItemList.stream().map(GetShopItemResponse::toDto).toList(), null);
    }

    @PostMapping("/shops/items")
    public ApiResponse<PostShopsItemsResponse> addShopItem(@RequestBody PostShopsItemsRequest postShopsItemsRequest){
        Item shopItem = PostShopsItemsRequest.toEntity(postShopsItemsRequest);
        Item saveShopItem = shopItemService.addShopItem(shopItem);
        return new ApiResponse<>(true, PostShopsItemsResponse.toDto(saveShopItem), null);
    }
}
