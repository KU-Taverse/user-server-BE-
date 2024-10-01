package ku.user.domain.inventory.controller;

import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.dto.response.GetInventoryResponse;
import ku.user.domain.inventory.service.InventoryService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
