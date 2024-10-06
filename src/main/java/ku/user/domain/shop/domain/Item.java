package ku.user.domain.shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Item {

    @Id
    private Long id;

    private String name;

    private String itemGroup;

    private String description;

    private int price;

    private int count;



}
