package ku.user.domain.shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shops")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private int price;

    private int count;



}
