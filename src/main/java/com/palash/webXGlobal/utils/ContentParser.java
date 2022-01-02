package com.palash.webXGlobal.utils;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ContentParser {

    /* Declaring the tags */
    private static final String GAME_CONTAINER = "div.card > .match-header-container";
    private static final String GAME_LIVE_STAT = "div.card > .live-stats > .run-rate-container";

    // container where result or status of the match is displayed
    private static final String GAME_HEADER_INFO = GAME_CONTAINER+" > .match-header-info";
    private static final String GAME_STATUS = GAME_HEADER_INFO+" .status span";
    private static final String GAME_DESCRIPTION = GAME_HEADER_INFO+" .description";

    // container where match details are contained
    private static final String GAME_INFO = GAME_CONTAINER+" > .match-header > .event > div > .match-info";
    private static final String TEAM_INFO = GAME_INFO+" > .teams > div";


    public Channel parse(Document document) {
        if (document == null) {
            return null;
        }

        Channel channel = new Channel();

        /*Elements elements = document.select(GAME_CONTAINER);*/

        Matches match = new Matches();

        Elements gameStatus = document.select(GAME_STATUS);
        match.setMatchStatus(gameStatus.html());

        Elements gameDescription = document.select(GAME_DESCRIPTION);
        match.setMatchDescription(gameDescription.text());

        Elements teams = document.select(TEAM_INFO);
        System.out.println("Elements: ");
        Elements team = document.select(TEAM_INFO+" .team");
        //for team 1  & 2

        match.setTeamA(team.get(0).select(".name").html());
        match.setTeamAImage(team.get(0).select("img[src$=.svg]").attr("src"));
        match.setTeamAScoreInfo(team.get(0).select(".score-info").html());
        match.setTeamAScore(team.get(0).select(".score").html());

        boolean winner =false;
        if (team.get(0).select(".winner-icon").attr("class").contains("winner-icon")) winner=true;
        match.setTeamAMatchResult(winner);

        boolean batting =false;
        if (team.get(0).select(".batting-indicator").attr("class").contains("batting-indicator")) batting=true;
        match.setTeamABatting(batting);


        match.setTeamB(team.get(1).select(".name").html());
        match.setTeamBImage(team.get(1).select("img[src$=.svg]").attr("src"));
        match.setTeamBScoreInfo(team.get(1).select(".score-info").html());
        match.setTeamBScore(team.get(1).select(".score").html());

        winner =false;
        if (team.get(1).select(".winner-icon").attr("class").contains("winner-icon")) winner=true;
        match.setTeamBMatchResult(winner);

        batting =false;
        if (team.get(1).select(".batting-indicator").attr("class").contains("batting-indicator")) batting=true;
        match.setTeamBBatting(batting);

        match.setDescription(document.select(".prematch-status .description").html());
        match.setPrematchStat(document.select(".prematch-status .status b").html());

        Elements gameInfo = document.select(GAME_INFO);
        match.setTossStat(gameInfo.select(".status-text span").html());

        Elements gameLiveStat = document.select(GAME_LIVE_STAT);
        match.setLiveStat(gameLiveStat.text());


        ScoreTimeline scoreTimeline = new ScoreTimeline();
        if(match.getTeamABatting()) {
            scoreTimeline.setBattingTeam(match.getTeamA());
            scoreTimeline.setScoreInfo(match.getTeamAScoreInfo());
            scoreTimeline.setScore(match.getTeamAScore());
        }
        if(match.getTeamBBatting()) {
            scoreTimeline.setBattingTeam(match.getTeamB());
            scoreTimeline.setScoreInfo(match.getTeamBScoreInfo());
            scoreTimeline.setScore(match.getTeamBScore());
        }

        scoreTimeline.setMatchInfo(match.getDescription());

        channel.setMatches(match);
        channel.setScoreTimeline(scoreTimeline);

        System.out.println("=> Details Collection END <=\n" );

        return channel;
    }

}
