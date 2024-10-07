package ku.user.domain.inventory.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class AlreadyPurchasedItemException extends CustomException {

    public AlreadyPurchasedItemException() {
        super(ErrorCode.ALREADY_PURCHASED_ERROR);
    }
}
