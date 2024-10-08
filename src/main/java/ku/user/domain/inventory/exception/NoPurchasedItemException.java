package ku.user.domain.inventory.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class NoPurchasedItemException extends CustomException {

    public NoPurchasedItemException(){
        super(ErrorCode.NO_PURCHASED_ERROR);
    }
}
