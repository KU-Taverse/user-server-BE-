package ku.user.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //global
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"G500","서버 내부에서 에러가 발생하였습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST,"G400","잘못된 입력입니다."),

    //auth
    INVALID_USER_INPUT(HttpStatus.BAD_REQUEST,"A400","잘못된 입력입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"A404","찾을 수 없는 유저입니다."),

    // ranking
    RANKING_NOT_FOUND(HttpStatus.NOT_FOUND, "R404", "랭킹 정보를 찾을 수 없습니다."),
    INVALID_RANKING_INPUT(HttpStatus.BAD_REQUEST, "R400", "잘못된 랭킹 입력 값입니다."),
    REDIS_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "R501", "Redis 서버 연결에 실패하였습니다."),
    RANKING_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "R502", "랭킹 업데이트에 실패하였습니다."),
    RANKING_GET_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "R503", "랭킹 조회 로직에서 문제가 발생하였습니다."),

    //quest
    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND,"Q404","퀘스트를 찾을 수 없습니다."),

    //character
    INVALID_CHARACTER_INPUT(HttpStatus.BAD_REQUEST,"C400","잘못된 입력입니다."),
    CONFLICT_CHARACTER_RESOURCE(HttpStatus.CONFLICT,"C409","캐릭터 리소스 충돌이 발생하였습니다."),
    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND,"C404","캐릭터를 찾을 수 없습니다."),

    //item
    ALREADY_PURCHASED_ERROR(HttpStatus.BAD_REQUEST,"I400","이미 구매한 아이템입니다."),
    NO_PURCHASED_ERROR(HttpStatus.FORBIDDEN,"I403","구매하지 않은 아이템입니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"I400","아이템을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
