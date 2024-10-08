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

    @Builder.Default
    private Long auraIndex = -1L;

    @Builder.Default
    private Long titleColorIndex = -1L;

    @Builder.Default
    private Long titleBackgroundIndex = -1L;

    public static Inventory from(Long characterId) {
        return Inventory.builder()
                .characterId(characterId)
                .build();
    }

    public void enableItem(List<Long> itemList) {
        auraIndex = itemList.get(0);
        titleColorIndex = itemList.get(1);
        titleBackgroundIndex = itemList.get(2);
    }
}
