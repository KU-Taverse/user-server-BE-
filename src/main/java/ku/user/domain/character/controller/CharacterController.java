package ku.user.domain.character.controller;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.dto.request.PutCharacterRequest;
import ku.user.domain.character.dto.request.SaveCharacterRequest;
import ku.user.domain.character.dto.response.*;
import ku.user.domain.character.service.CharacterService;
import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.service.InventoryService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final InventoryService inventoryService;

    @GetMapping("/characters/check")
    public ApiResponse<CheckCharacterExistResponse> checkCharacterExist(@RequestParam() String email) {
        Boolean existByEmail = characterService.checkCharacterExistByEmail(email);
        CheckCharacterExistResponse checkCharacterExistResponse = CheckCharacterExistResponse.toDto(existByEmail);
        return new ApiResponse<>(true, checkCharacterExistResponse, null);
    }

    @PostMapping("/characters")
    public ApiResponse<SaveCharacterResponse> saveCharacter(@RequestBody SaveCharacterRequest saveCharacterRequest) {
        Character character = SaveCharacterRequest.toEntity(saveCharacterRequest);
        Character saveCharacter = characterService.saveByEmail(character, saveCharacterRequest.getEmail());
        SaveCharacterResponse saveCharacterResponse = SaveCharacterResponse.toDto(saveCharacter);
        return new ApiResponse<>(true, saveCharacterResponse, null);
    }

    @GetMapping("/characters")
    public ApiResponse<FindCharacterByEmailResponse> findCharacterByEmail(@RequestParam() String email) {
        Character character = characterService.findByEmail(email);
        Inventory inventory = inventoryService.getInventoryByEmail(email);
        FindCharacterByEmailResponse findCharacterByEmailResponse = FindCharacterByEmailResponse.toDto(inventory,character);
        return new ApiResponse<>(true, findCharacterByEmailResponse, null);
    }

    @PutMapping("/characters")
    public ApiResponse<PutCharacterResponse> modifyCharacter(@RequestBody PutCharacterRequest putCharacterRequest){
        Character character = PutCharacterRequest.toEntity(putCharacterRequest);
        Character modifiedCharacter = characterService.modifiedByEmail(putCharacterRequest.getEmail(), character);
        PutCharacterResponse putCharacterResponse = PutCharacterResponse.toDto(modifiedCharacter);
        return new ApiResponse<>(true, putCharacterResponse, null);
    }

    @DeleteMapping("/characters")
    public ApiResponse<DeleteCharacterResponse> deleteCharacter(@RequestParam String email) {
        characterService.deleteByEmail(email);
        DeleteCharacterResponse deleteCharacterResponse = DeleteCharacterResponse.toDto(true);
        return new ApiResponse<>(true, deleteCharacterResponse, null);
    }


}
