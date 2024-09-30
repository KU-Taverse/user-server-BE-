package ku.user.domain.ranking.service;

import ku.user.domain.ranking.domain.RhythmScore;

import java.time.LocalDateTime;
import java.util.List;

public interface RhythmScoreService {
    // 생성
    RhythmScore saveScore(RhythmScore rhythmScore);

    // 조회(해당 캐릭터의 모든 기록)
    List<RhythmScore> findScoresByNickName(String nickName);

    // 조회(해당 캐릭터의 특정 날짜 기록)
    List<RhythmScore> findScoresByNickNameAndDate(String nickName, LocalDateTime startDate, LocalDateTime endDate);

    // 캐릭터 삭제
    void updateScoreStatus(String nickName);

    // 해당 캐릭터의 최고 기록
    RhythmScore findHighestScoreByNickName(String nickName);
}
