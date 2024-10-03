package ku.user.domain.inventory.dto.request;

import ku.user.domain.inventory.domain.ItemType;
import lombok.Getter;

@Getter
public class PostItemRequest {

    private ItemType itemType;
}
