package ku.user.domain.inventory.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "inventorys")
@Builder
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long characterId;

    @OneToMany(mappedBy = "inventory")
    @Builder.Default
    private List<Item> itemList = new ArrayList<>();

    public static Inventory from(Long characterId) {
        return Inventory.builder()
                .characterId(characterId)
                .build();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }
}
