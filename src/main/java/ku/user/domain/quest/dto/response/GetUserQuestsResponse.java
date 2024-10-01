package ku.user.domain.quest.dto.response;

import ku.user.domain.quest.domain.Quest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
public record GetUserQuestsResponse (int firstQuestIndex,
                                     int secondQuestIndex,
                                     int thirdQuestIndex,
                                     Boolean firstQuestClear,
                                     Boolean secondQuestClear,
                                     Boolean thirdQuestClear){

    public static GetUserQuestsResponse toDto(Quest quest){
        return GetUserQuestsResponse.builder()
                .firstQuestIndex(quest.getQuest1())
                .secondQuestIndex(quest.getQuest2())
                .thirdQuestIndex(quest.getQuest3())
                .firstQuestClear(quest.getQuest1CompletedAt() != null)
                .secondQuestClear(quest.getQuest2CompletedAt() != null)
                .thirdQuestClear(quest.getQuest3CompletedAt() != null)
                .build();
    }
}
