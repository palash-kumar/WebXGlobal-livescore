package com.palash.webXGlobal.repositories;

import com.palash.webXGlobal.entities.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchesRepo extends JpaRepository<Matches, Long> {

    @Query("SELECT m FROM Matches m where m.teamA = :teamA and m.teamB = :teamB and m.matchDescription = :matchDescription")
    Matches findMatch(@Param("teamA") String teamA, @Param("teamB") String teamB, @Param("matchDescription") String matchDescription);

    @Query("select m from Matches m where m.teamAMatchResult=false and m.teamBMatchResult=false order by m.id")
    List<Matches> findMatches();

    @Query("SELECT m FROM Matches m where lower(m.teamA) like :team_name% or lower(m.teamB) like :team_name% ")
    List<Matches> findMatchesByTeam(@Param("team_name") String team_name);

    @Query("select m from Matches m where m.teamAMatchResult=true or m.teamBMatchResult=true order by m.id")
    List<Matches> findFinishedMatches();
}
