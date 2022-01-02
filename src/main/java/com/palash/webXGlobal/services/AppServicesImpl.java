package com.palash.webXGlobal.services;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import com.palash.webXGlobal.entities.Users;
import com.palash.webXGlobal.repositories.MatchesRepo;
import com.palash.webXGlobal.repositories.ScoreTimelineRepo;
import com.palash.webXGlobal.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppServicesImpl implements AppServices{

    private UsersRepo usersRepo;
    private MatchesRepo matchesRepo;
    private ScoreTimelineRepo scoreTimelineRepo;

    @Autowired
    AppServicesImpl(UsersRepo usersRepo, MatchesRepo matchesRepo, ScoreTimelineRepo scoreTimelineRepo){
        this.usersRepo = usersRepo;
        this.matchesRepo = matchesRepo;
        this.scoreTimelineRepo = scoreTimelineRepo;
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    @Override
    public Users saveUser(Users users) {
        return usersRepo.save(users);
    }

    @Override
    public Matches findMatch(String teamA, String teamB, String matchDescription) {
        return matchesRepo.findMatch(teamA, teamB, matchDescription);
    }

    @Override
    public Matches saveMatch(Matches match) {
        return matchesRepo.save(match);
    }

    @Override
    public ScoreTimeline getScoreTimeLine(Matches matchId, String battingTeam) {
        System.out.println("getScoreTimeLine: matchId="+matchId.getId()+"; team="+battingTeam);
        return scoreTimelineRepo.getScoreTimeLine(matchId, battingTeam);
    }

    @Override
    public ScoreTimeline saveScoreTimeLine(ScoreTimeline scoreTimeline) {
        return scoreTimelineRepo.save(scoreTimeline);
    }

    @Override
    public List<Matches> findMatches() {
        return matchesRepo.findMatches();
    }

    @Override
    public List<Map<String, String>> findTeamMatchList(String team_name) {

        List<Map<String, String>> matchMap = new ArrayList<>();

        List<Matches> matchesList = matchesRepo.findMatchesByTeam(team_name);
        System.out.println("matchesList: "+matchesList.size());
        for (Matches match : matchesList){
            HashMap<String, String> hm = new HashMap<>();

            hm.put("id", match.getId().toString());
            hm.put("text", match.getTeamA()+" vs. "+match.getTeamB());
            matchMap.add(hm);

        }


        return matchMap;
    }

    @Override
    public Matches findMatchById(Long id) {
        return matchesRepo.getById(id);
    }

    @Override
    public List<Matches> findFinishedMatches() {
        return matchesRepo.findFinishedMatches();
    }

    @Override
    public DataTablesOutput<ScoreTimeline> findAllScoresForMatch(DataTablesInput input, Specification<ScoreTimeline> scoreTimeline_specifications) {
        return scoreTimelineRepo.findAll(input, scoreTimeline_specifications);
    }
}
