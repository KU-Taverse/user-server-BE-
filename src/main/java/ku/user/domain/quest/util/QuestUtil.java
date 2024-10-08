package ku.user.domain.quest.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class QuestUtil {

    private QuestUtil() {
    }

    public static int QUEST_COUNT = 7;
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Integer getRandomQuest() {
        return random.nextInt(QUEST_COUNT) + 1;
    }

    public static List<Integer> getRandomQuests(int selectCount) {
        Set<Integer> set = new HashSet<>();
        while (set.size() < selectCount) {
            Integer randomQuest = getRandomQuest();
            set.add(randomQuest);
        }
        return set.stream().toList();
    }
}
