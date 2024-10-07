package ku.user.domain.inventory.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PutItemRequest {
    //아이템 착용 API에 사용되는 request

    private List<Integer> useItemList;
}
