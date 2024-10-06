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

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Inventory inventory;

    @ManyToOne
    private Item item;

    public static InventoryItem from(Inventory inventory, ItemType itemType){
        return InventoryItem.builder()
                .itemType(itemType)
                .inventory(inventory)
                .build();
    }
}
