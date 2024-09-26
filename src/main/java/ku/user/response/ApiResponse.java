package ku.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T>{
    private Boolean isSuccess;
    private T response;
    private ErrorResponse errorResponse;

    public ApiResponse(Boolean isSuccess, T response, ErrorResponse errorResponse) {
        this.isSuccess = isSuccess;
        this.response = response;
        this.errorResponse = errorResponse;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public T getResponse() {
        return response;
    }
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
