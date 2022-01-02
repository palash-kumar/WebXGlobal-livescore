package com.palash.webXGlobal.repositories;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreTimelineRepo extends JpaRepository<ScoreTimeline, Long>, DataTablesRepository<ScoreTimeline, Long> {

    @Query("SELECT s FROM ScoreTimeline s WHERE s.id = (SELECT s1.id FROM ScoreTimeline s1 WHERE s1.matchId = :matchId and s1.battingTeam = :battingTeam and s1.battingTeam is not null )")
    ScoreTimeline getScoreTimeLine(@Param("matchId") Matches matchId, @Param("battingTeam") String battingTeam);
}
