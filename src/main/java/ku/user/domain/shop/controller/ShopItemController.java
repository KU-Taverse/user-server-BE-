package ku.user.domain.shop.controller;

import ku.user.domain.shop.domain.ShopItem;
import ku.user.domain.shop.dto.response.GetShopItemResponse;
import ku.user.domain.shop.service.ShopItemService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopItemController {

    private final ShopItemService shopItemService;

    @GetMapping("/shops/items")
    public ApiResponse<List<GetShopItemResponse>> getShopList() {
        List<ShopItem> shopItemList = shopItemService.findAll();
        return new ApiResponse<>(true, shopItemList.stream().map(GetShopItemResponse::toDto).toList(), null);
    }
}
