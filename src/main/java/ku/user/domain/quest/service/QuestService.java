package ku.user.domain.quest.service;

import ku.user.domain.quest.dao.QuestRepository;
import ku.user.domain.quest.dao.UserQuestRepository;
import ku.user.domain.quest.domain.Quest;
import ku.user.domain.quest.domain.UserQuest;
import ku.user.domain.quest.util.QuestUtil;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final UserQuestRepository userQuestRepository;
    private final QuestRepository questRepository;
    private final UserService userService;

    @Transactional
    public UserQuest save(UserQuest quest) {
        return userQuestRepository.save(quest);
    }

    @Transactional
    public UserQuest findUserQuestByUserId(Long userId) {
        return userQuestRepository.findByUserId(userId).get();
    }

    @Transactional
    public Quest findQuestById(Long questId) {
        Optional<Quest> questOptional = questRepository.findById(questId);
        if (questOptional.isEmpty())
            throw new RuntimeException("찾을 수 없는 퀘스트 입니다");
        return questOptional.get();
    }

    /**
     * 유저의 퀘스트를 조회한다.(가능하면 갱신한다)
     *
     * @param email
     * @return quest
     */
    @Transactional
    public UserQuest checkAndRefreshQuest(String email) {
        Long userId = userService.getByEmail(email).getId();
        LocalDate localDate = LocalDate.now();
        Optional<UserQuest> questOptional = userQuestRepository.findQuestByUserIdAndCompletionDate(userId, localDate);
        return questOptional.orElseGet(() -> createDailyQuest(userId));
    }

    /**
     * 새로운 퀘스트를 생성한다
     *
     * @param userId
     * @return quest
     */
    @Transactional
    public UserQuest createDailyQuest(Long userId) {
        List<Long> randomQuestNumber = QuestUtil.getRandomQuests(3);
        UserQuest quest = UserQuest.from(userId, randomQuestNumber);
        return save(quest);
    }

    /**
     * 퀘스트를 해결한다
     * 현재 사용하지 않는다
     * @param questIndex 퀘스트 번호
     * @param email
     * @return
     */
    @Transactional
    public UserQuest solveQuestByEmail(Long questIndex, String email) {
        Long userId = userService.getByEmail(email).getId();
        UserQuest findQuest = findUserQuestByUserId(userId);
        findQuest.solve(questIndex);
        return findQuest;
    }

    /**
     * map을 바탕으로 유저 퀘스트 정보를 업데이트한다.
     * 만약 이미 완료된 퀘스트라면 업데이트 하지 않는다
     *
     * @param userQuestMap
     * @param email
     * @return
     */
    @Transactional
    public UserQuest updateQuestProgress(Map<Long, Integer> userQuestMap, String email) {
        Long userId = userService.getByEmail(email).getId();
        UserQuest findUserQuest = findUserQuestByUserId(userId);

        //첫 번째 퀘스트
        if (findUserQuest.getQuest1CompletedAt() == null) {
            Long questId = findUserQuest.getQuest1();
            Integer questProgress = userQuestMap.get(questId);
            findUserQuest.updateQuest1(questProgress);

            //퀘스트 성공시 성공 로직 실행
            Quest findQuest = findQuestById(questId);
            if (findQuest.getClearCondition() <= findUserQuest.getQuest1Progress()) {
                findUserQuest.solve(questId);
            }
        }

        //두 번째 퀘스트
        if (findUserQuest.getQuest2CompletedAt() == null) {
            Long questId = findUserQuest.getQuest2();
            Integer questProgress = userQuestMap.get(questId);
            findUserQuest.updateQuest2(questProgress);

            Quest findQuest = findQuestById(questId);
            if (findQuest.getClearCondition() <= findUserQuest.getQuest2Progress()) {
                findUserQuest.solve(questId);
            }
        }

        //세 번째 퀘스트
        if (findUserQuest.getQuest3CompletedAt() == null) {
            Long questId = findUserQuest.getQuest3();
            Integer questProgress = userQuestMap.get(questId);
            findUserQuest.updateQuest3(questProgress);

            //퀘스트 성공시 성공 로직 실행
            Quest findQuest = findQuestById(questId);
            if (findQuest.getClearCondition() <= findUserQuest.getQuest3Progress()) {
                findUserQuest.solve(questId);
            }
        }
        return findUserQuest;
    }
}
