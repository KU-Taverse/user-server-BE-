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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public T getResponse() {
        return response;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
