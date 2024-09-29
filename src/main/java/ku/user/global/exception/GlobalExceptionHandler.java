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
    private ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
        return responseException(ErrorCode.INTERNAL_SERVER_ERROR,ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),exception);
    }
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<?> handleCustomException(CustomException exception) {
        return responseException(exception.getErrorCode(),exception.getMessage(),exception);
    }

    private ResponseEntity<?> responseException(ErrorCode errorCode,String message, Exception e) {
        log.error("{}: {} : {}", errorCode, errorCode.getMessage(), e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(errorCode,message);
        ApiResponse<?> apiResponse = ApiResponse.fromError(errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(apiResponse);

    }



}
