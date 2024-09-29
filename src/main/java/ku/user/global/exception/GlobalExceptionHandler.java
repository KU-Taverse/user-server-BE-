package ku.user.global.exception;

import ku.user.global.response.ApiResponse;
import ku.user.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return responseException(ErrorCode.INTERNAL_SERVER_ERROR,e);
    }

    private ResponseEntity<?> responseException(ErrorCode errorCode, Exception e) {
        log.error("{}: {} : {}", errorCode, errorCode.getMessage(), e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        ApiResponse<?> apiResponse = ApiResponse.fromError(errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(apiResponse);

    }



}
