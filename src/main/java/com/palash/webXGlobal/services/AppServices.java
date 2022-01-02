package com.palash.webXGlobal.services;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import com.palash.webXGlobal.entities.Users;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface AppServices {
    Users findByUsername(String username);

    Users saveUser(Users users);

    Matches findMatch(String teamA, String teamB, String matchDescription);

    Matches saveMatch(Matches match);

    ScoreTimeline getScoreTimeLine(Matches matchId, String battingTeam);

    ScoreTimeline saveScoreTimeLine(ScoreTimeline scoreTimeline);

    List<Matches> findMatches();

    List<Map<String, String>> findTeamMatchList(String team_name);

    Matches findMatchById(Long id);

    List<Matches> findFinishedMatches();

    DataTablesOutput<ScoreTimeline> findAllScoresForMatch(DataTablesInput input, Specification<ScoreTimeline> scoreTimeline_specifications);
}
