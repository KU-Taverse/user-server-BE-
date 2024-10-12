package ku.user.domain.quest.dto.response;

import ku.user.domain.quest.domain.UserQuest;
import lombok.Builder;


@Builder
public record GetUserQuestsResponse (Long firstQuestIndex,
                                     Long secondQuestIndex,
                                     Long thirdQuestIndex,
                                     Boolean firstQuestClear,
                                     Boolean secondQuestClear,
                                     Boolean thirdQuestClear,
                                     int firstQuestProgress,
                                     int secondQuestProgress,
                                     int thirdQuestProgress){

    public static GetUserQuestsResponse toDto(UserQuest quest){
        return GetUserQuestsResponse.builder()
                .firstQuestIndex(quest.getQuest1())
                .secondQuestIndex(quest.getQuest2())
                .thirdQuestIndex(quest.getQuest3())
                .firstQuestClear(quest.getQuest1CompletedAt() != null)
                .secondQuestClear(quest.getQuest2CompletedAt() != null)
                .thirdQuestClear(quest.getQuest3CompletedAt() != null)
                .firstQuestProgress(quest.getQuest1Progress())
                .secondQuestProgress(quest.getQuest2Progress())
                .secondQuestProgress(quest.getQuest3Progress())
                .build();
    }
}
