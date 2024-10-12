package ku.user.domain.quest.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class PostUserQuestRequest {

    private Long firstQuestIndex;
    private Long secondQuestIndex;
    private Long thirdQuestIndex;

    private Integer firstQuestProgress;
    private Integer secondQuestProgress;
    private Integer thirdQuestProgress;

    public Map<Long, Integer> getUserQuestMap(){
        Map<Long, Integer> map = new HashMap<>();
        map.put(firstQuestIndex,firstQuestProgress);
        map.put(secondQuestIndex,secondQuestProgress);
        map.put(thirdQuestIndex,thirdQuestProgress);
        return map;
    }
}
