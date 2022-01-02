package com.palash.webXGlobal.utils;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;

import java.util.Date;
import java.util.List;

public class Channel {

    String channel;
    Integer ttl;
    String link;
    String description;
    String copyright;
    String language;
    Date pubDate;

    List<Item> itemList;

    Matches matches;

    ScoreTimeline scoreTimeline;

    public Channel() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }

    public ScoreTimeline getScoreTimeline() {
        return scoreTimeline;
    }

    public void setScoreTimeline(ScoreTimeline scoreTimeline) {
        this.scoreTimeline = scoreTimeline;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channel='" + channel + '\'' +
                ", ttl=" + ttl +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", copyright='" + copyright + '\'' +
                ", language='" + language + '\'' +
                ", pubDate=" + pubDate +
                ", itemList=" + itemList +
                '}';
    }
}
