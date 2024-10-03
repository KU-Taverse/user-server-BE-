package ku.user.domain.shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shops")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long name;

    private String description;
    private int price;

    private int count;



}
