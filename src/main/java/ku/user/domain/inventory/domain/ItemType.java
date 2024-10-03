package ku.user.domain.inventory.domain;

import lombok.Getter;

/**
 * 곧 삭제될 enum
 */
@Getter
public enum ItemType {

    //오로라
    AURORA(1L, 100, "오로라"),
    //킥보드
    KICK_BOARD(2L, 100, "킥보드"),
    //칭호
    TITLE(3L, 100, "칭호");
    private final Long itemNumber;
    private final int price;
    private final String itemName;

    ItemType(Long itemNumber, int price, String itemName) {
        this.itemNumber = itemNumber;
        this.price = price;
        this.itemName = itemName;
    }

    public static ItemType findItemTypeByName(String itemName) {
        if(itemName.equals(AURORA.itemName))
            return ItemType.AURORA;
        if(itemName.equals(KICK_BOARD.itemName))
            return ItemType.KICK_BOARD;
        return ItemType.TITLE;
    }
}
