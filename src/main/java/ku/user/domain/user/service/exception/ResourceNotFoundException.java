package ku.user.domain.user.service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg, long id) {
        super(msg + "에서 ID " + id + "를 찾을 수 없습니다.");
    }

    public ResourceNotFoundException(String msg, String email) {
        super(msg + "에서 ID " + email + "를 찾을 수 없습니다.");
    }
}
