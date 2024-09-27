package ku.user.domain.character.dto.response;

import lombok.Builder;

@Builder
public record DeleteCharacterResponse(boolean isDelete) {
    public static DeleteCharacterResponse toDto(Boolean isDelete){
        return DeleteCharacterResponse.builder()
                .isDelete(isDelete)
                .build();
    }
}
