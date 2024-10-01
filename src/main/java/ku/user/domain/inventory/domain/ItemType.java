package ku.user.domain.inventory.domain;

import lombok.Getter;

@Getter
public enum ItemType {

    //오로라
    AURORA(1L, 100),
    //킥보드
    KICK_BOARD(2L, 100),
    //칭호
    TITLE(3L, 100);
    private Long itemNumber;
    private int price;

    ItemType(Long itemNumber, int price) {
        this.itemNumber = itemNumber;
        this.price = price;
    }
}
