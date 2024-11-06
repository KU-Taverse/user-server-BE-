package ku.user.domain.inventory.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class NotFoundInventoryException extends CustomException {

    public NotFoundInventoryException(){
        super(ErrorCode.INVENTORY_NOT_FOUND);
    }
}
