package ku.user.domain.inventory.domain;

import jakarta.persistence.*;
import ku.user.domain.shop.domain.Item;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "inventorys_items")
@Builder
@Getter
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO 제거 했으면 좋겠다
    /*@ManyToOne
    @JoinColumn(name = "item_id")
    private Inventory inventory;*/
    private Long inventoryId;

    @ManyToOne
    private Item item;

    public static InventoryItem from(Inventory inventory, Item item){
        return InventoryItem.builder()
                .inventoryId(inventory.getId())
                .item(item)
                .build();
    }
}
