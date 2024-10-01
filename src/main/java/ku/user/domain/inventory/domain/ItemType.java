package ku.user.domain.inventory.domain;

import lombok.Getter;

@Getter
public enum ItemType {

    //오로라
    AURORA(1L),
    //킥보드
    KICK_BOARD(2L),
    //칭호
    TITLE(3L)
    ;
    private Long itemNumber;
    ItemType(Long itemNumber){
        this.itemNumber = itemNumber;
    }
}
