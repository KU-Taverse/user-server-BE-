package ku.user.domain.user.service.exception;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String msg) {
        super(msg);
    }
}
