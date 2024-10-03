package ku.user.domain.inventory.controller;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.ItemType;
import ku.user.domain.inventory.dto.request.PostItemRequest;
import ku.user.domain.inventory.dto.response.GetInventoryResponse;
import ku.user.domain.inventory.dto.response.PostItemResponse;
import ku.user.domain.inventory.service.InventoryService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventorys")
    public ApiResponse<GetInventoryResponse> GetInventory(@RequestParam String email) {
        Inventory inventory = inventoryService.getInventoryByEmail(email);
        GetInventoryResponse getInventoryResponse = GetInventoryResponse.toDto(inventory);
        return new ApiResponse<>(true,getInventoryResponse,null);
    }

    @PostMapping("/inventorys")
    public ApiResponse<PostItemResponse> PostItem(@RequestParam String email, @RequestBody PostItemRequest postItemRequest) {
        Inventory inventory = inventoryService.buyItem(email, postItemRequest.getItemType());
        PostItemResponse postItemResponse = PostItemResponse.toDto(inventory);
        return new ApiResponse<>(true,postItemResponse,null);
    }
}
