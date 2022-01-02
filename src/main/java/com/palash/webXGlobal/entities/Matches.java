package com.palash.webXGlobal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Matches implements Serializable {

    @Id
    @SequenceGenerator(name= "MATCH_SQ", sequenceName = "MATCH_SQ_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="MATCH_SQ")
    private Long id;

    @Column(length = 255)
    private String matchDescription;

    @Column
    private String matchStatus;

    @Column
    private String teamA;

    @Column
    private String teamAImage;

    @Column
    private String teamAScoreInfo;

    @Column
    private String teamAScore;

    @Column
    private Boolean teamAMatchResult = false;

    @Column
    private Boolean teamABatting = false;

    @Column
    private String teamB;

    @Column
    private String teamBImage;

    @Column
    private String teamBScoreInfo;

    @Column
    private String teamBScore;

    @Column
    private Boolean teamBMatchResult = false;

    @Column
    private Boolean teamBBatting = false;

    @Column
    private String description;

    @Column
    private String prematchStat;

    @Column
    private String tossStat;

    @Column
    private Date matchDate;

    @Column
    private Date updateTime;

    @Column
    private String liveStat = "";

    @JsonBackReference
    @OneToMany(mappedBy = "matchId")
    private List<ScoreTimeline> scoreTimelines =new ArrayList<ScoreTimeline>();

    public Matches() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchDescription() {
        return matchDescription;
    }

    public void setMatchDescription(String matchDescription) {
        this.matchDescription = matchDescription;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamAImage() {
        return teamAImage;
    }

    public void setTeamAImage(String teamAImage) {
        this.teamAImage = teamAImage;
    }

    public String getTeamAScoreInfo() {
        return teamAScoreInfo;
    }

    public void setTeamAScoreInfo(String teamAScoreInfo) {
        this.teamAScoreInfo = teamAScoreInfo;
    }

    public String getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(String teamAScore) {
        this.teamAScore = teamAScore;
    }

    public Boolean getTeamAMatchResult() {
        return teamAMatchResult;
    }

    public void setTeamAMatchResult(Boolean teamAMatchResult) {
        this.teamAMatchResult = teamAMatchResult;
    }

    public Boolean getTeamABatting() {
        return teamABatting;
    }

    public void setTeamABatting(Boolean teamABatting) {
        this.teamABatting = teamABatting;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTeamBImage() {
        return teamBImage;
    }

    public void setTeamBImage(String teamBImage) {
        this.teamBImage = teamBImage;
    }

    public String getTeamBScoreInfo() {
        return teamBScoreInfo;
    }

    public void setTeamBScoreInfo(String teamBScoreInfo) {
        this.teamBScoreInfo = teamBScoreInfo;
    }

    public String getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(String teamBScore) {
        this.teamBScore = teamBScore;
    }

    public Boolean getTeamBMatchResult() {
        return teamBMatchResult;
    }

    public void setTeamBMatchResult(Boolean teamBMatchResult) {
        this.teamBMatchResult = teamBMatchResult;
    }

    public Boolean getTeamBBatting() {
        return teamBBatting;
    }

    public void setTeamBBatting(Boolean teamBBatting) {
        this.teamBBatting = teamBBatting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrematchStat() {
        return prematchStat;
    }

    public void setPrematchStat(String prematchStat) {
        this.prematchStat = prematchStat;
    }

    public String getTossStat() {
        return tossStat;
    }

    public void setTossStat(String tossStat) {
        this.tossStat = tossStat;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLiveStat() {
        return liveStat;
    }

    public void setLiveStat(String liveStat) {
        this.liveStat = liveStat;
    }

    public List<ScoreTimeline> getScoreTimelines() {
        return scoreTimelines;
    }

    public void setScoreTimelines(List<ScoreTimeline> scoreTimelines) {
        this.scoreTimelines = scoreTimelines;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "id=" + id +
                ", matchDescription='" + matchDescription + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                ", teamA='" + teamA + '\'' +
                ", teamAImage='" + teamAImage + '\'' +
                ", teamAScoreInfo='" + teamAScoreInfo + '\'' +
                ", teamAScore='" + teamAScore + '\'' +
                ", teamAMatchResult=" + teamAMatchResult +
                ", teamABatting=" + teamABatting +
                ", teamB='" + teamB + '\'' +
                ", teamBImage='" + teamBImage + '\'' +
                ", teamBScoreInfo='" + teamBScoreInfo + '\'' +
                ", teamBScore='" + teamBScore + '\'' +
                ", teamBMatchResult=" + teamBMatchResult +
                ", teamBBatting=" + teamBBatting +
                ", description='" + description + '\'' +
                ", prematchStat='" + prematchStat + '\'' +
                ", tossStat='" + tossStat + '\'' +
                ", matchDate=" + matchDate +
                ", updateTime=" + updateTime +
                ", liveStat='" + liveStat + '\'' +
                '}';
    }
}
