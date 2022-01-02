package com.palash.webXGlobal.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ScoreTimeline implements Serializable {

    @Id
    @SequenceGenerator(name= "SCORE_SQ", sequenceName = "SCORE_SQ_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="SCORE_SQ")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private Matches matchId;

    @Column
    private String battingTeam;

    @Column
    private String matchInfo;

    @Column
    private String scoreInfo;

    @Column
    private String score;

    @Column
    private Date recordedAt = new Date();

    public ScoreTimeline() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Matches getMatchId() {
        return matchId;
    }

    public void setMatchId(Matches matchId) {
        this.matchId = matchId;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(String matchInfo) {
        this.matchInfo = matchInfo;
    }

    public String getScoreInfo() {
        return scoreInfo;
    }

    public void setScoreInfo(String scoreInfo) {
        this.scoreInfo = scoreInfo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    @Override
    public String toString() {
        return "ScoreTimeline{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", battingTeam='" + battingTeam + '\'' +
                ", matchInfo='" + matchInfo + '\'' +
                ", scoreInfo='" + scoreInfo + '\'' +
                ", score='" + score + '\'' +
                ", recordedAt=" + recordedAt +
                '}';
    }
}
