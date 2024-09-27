package ku.user.domain.character.dto.response;

import lombok.Builder;

@Builder
public record CheckCharacterExistResponse(Boolean isExist) {
    public static CheckCharacterExistResponse toDto(Boolean isExist){
        return CheckCharacterExistResponse.builder()
                .isExist(isExist)
                .build();
    }
}


