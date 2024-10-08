package ku.user.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //global
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"G500","서버 내부에서 에러가 발생하였습니다"),

    //item
    ALREADY_PURCHASED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"I501","이미 구매한 아이템입니다"),
    NO_PURCHASED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"I502","구매하지 않은 아이템입니다")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
