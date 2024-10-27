package ku.user.domain.inventory.controller;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.InventoryItem;
import ku.user.domain.inventory.dto.request.PostItemRequest;
import ku.user.domain.inventory.dto.request.PutItemRequest;
import ku.user.domain.inventory.dto.response.GetInventoryResponse;
import ku.user.domain.inventory.dto.response.PostItemResponse;
import ku.user.domain.inventory.service.InventoryService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventorys")
    public ApiResponse<GetInventoryResponse> GetInventory(@RequestParam String email) {
        Inventory inventory = inventoryService.getInventoryByEmail(email);
        List<InventoryItem> inventoryItemList = inventoryService.getInventoryItemList(inventory.getId());
        GetInventoryResponse getInventoryResponse = GetInventoryResponse.toDto(inventory, inventoryItemList);
        return new ApiResponse<>(true, getInventoryResponse, null);
    }

    @PostMapping(value = "/inventorys",
    consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiResponse<PostItemResponse> PostItem(@RequestParam String email, PostItemRequest postItemRequest) {
        Inventory inventory = inventoryService.buyItem(email, postItemRequest.getItemIndex());
        PostItemResponse postItemResponse = PostItemResponse.toDto(inventory);
        return new ApiResponse<>(true, null, null);
    }


    @PutMapping("/inventorys")
    public ApiResponse<Void> PutItem(@RequestParam String email, @RequestBody PutItemRequest putItemRequest) {
        Inventory inventory = inventoryService.enableItemsByEmail(putItemRequest.getUseItemList(), email);
        return new ApiResponse<>(true, null, null);
    }
}
