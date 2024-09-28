package ku.user.domain.Ranking.service;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.domain.Status;

import java.time.LocalDate;
import java.util.List;

public interface RhythmScoreService {
    // 생성
    RhythmScore saveScore(RhythmScore rhythmScore);

    // 조회(해당 캐릭터의 모든 기록)
    List<RhythmScore> findScoresByNickName(String nickName);

    // 조회(해당 캐릭터의 특정 날짜 기록)
    List<RhythmScore> findScoresByNickNameAndDate(String nickName, LocalDate date);

    // 캐릭터 삭제
    void updateScoreStatus(String nickName);

    // 해당 캐릭터의 최고 기록
    RhythmScore findHighestScoreByNickName(String nickName);
}
