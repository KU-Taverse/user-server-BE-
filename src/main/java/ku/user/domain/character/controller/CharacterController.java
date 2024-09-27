package ku.user.domain.character.controller;

import ku.user.domain.character.dto.response.CheckCharacterExistResponse;
import ku.user.domain.character.service.CharacterService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/characters/check")
    public ApiResponse<CheckCharacterExistResponse> checkCharacterExist(@RequestParam() String email) {
        Boolean existByEmail = characterService.checkCharacterExistByEmail(email);
        CheckCharacterExistResponse checkCharacterExistResponse = CheckCharacterExistResponse.toDto(existByEmail);
        return new ApiResponse<>(true, checkCharacterExistResponse, null);
    }

    @PostMapping("/characters")
    public String saveCharacter() {
        return "success";
    }

    @PostMapping("/characters/32")
    public String getCharater() {
        return "success";
    }
}
