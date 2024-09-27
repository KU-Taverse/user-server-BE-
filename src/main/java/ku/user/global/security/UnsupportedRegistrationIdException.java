package ku.user.global.security;

public class UnsupportedRegistrationIdException  extends RuntimeException {
    public UnsupportedRegistrationIdException(String message) {
        super(message);
    }
}
