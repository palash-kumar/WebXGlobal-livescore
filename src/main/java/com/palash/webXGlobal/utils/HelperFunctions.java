package com.palash.webXGlobal.utils;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import com.palash.webXGlobal.services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelperFunctions {

    private final AppServices appServices;

    @Autowired
    public HelperFunctions(final AppServices appServices){
        this.appServices = appServices;
    }

    public void checkSession(Principal principal, HttpSession session) {
        System.out.println("Principal: "+principal.getName());
        if (session.getAttribute("CurrentUser") == null )
            session.setAttribute("CurrentUser", appServices.findByUsername(principal.getName()));
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Channel parseChannel(){

        WebsiteParser websiteParser = new WebsiteParser();
        ContentParser contentParser = new ContentParser();

        String url = "http://static.cricinfo.com/rss/livescores.xml";
        List<Channel> channels = new ArrayList<Channel>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);

            // normalize XML response
            doc.getDocumentElement().normalize();

            System.out.println("doc: "+doc.getElementsByTagName("title").item(0).getTextContent());

            Channel channel = new Channel();
            channel.setChannel(doc.getElementsByTagName("title").item(0).getTextContent());
            channel.setTtl(Integer.parseInt(doc.getElementsByTagName("ttl").item(0).getTextContent()));
            channel.setLink(doc.getElementsByTagName("link").item(0).getTextContent());
            channel.setDescription(doc.getElementsByTagName("description").item(0).getTextContent());
            channel.setCopyright(doc.getElementsByTagName("copyright").item(0).getTextContent());
            channel.setLanguage(doc.getElementsByTagName("language").item(0).getTextContent());

            System.out.println("channel: "+channel.toString());

            //read Item list
            NodeList xmlItemList = doc.getElementsByTagName("item");
            System.out.println("xmlItemList: "+xmlItemList.getLength());
            List<Item> itemList = new ArrayList<Item>();

            for (Node node: XmlUtil.asList(xmlItemList)){
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element) node;
                    Item item = new Item();
                    item.setTitle(elem.getElementsByTagName("title").item(0).getTextContent());
                    item.setLink(elem.getElementsByTagName("link").item(0).getTextContent());
                    item.setDescription(elem.getElementsByTagName("description").item(0).getTextContent());
                    item.setGuid(elem.getElementsByTagName("guid").item(0).getTextContent());

                    itemList.add(item);
                }

            }

            channel.setItemList(itemList);


            for (Item itm : itemList){
                channels.add(contentParser.parse(websiteParser.testParser(itm.getLink())));
            }

            /* Saving the information to DB */
            iterateOverElements(channels);
            /**/

        }catch (Exception e){
            e.printStackTrace();
        }

        return new Channel();
    }

    private void iterateOverElements(List<Channel> channels){
        for (Channel channel : channels){
            Matches matches = channel.getMatches();
            System.out.println("match: " + matches.toString());
            matches = validateSaveOrUpdate(matches, channel.getScoreTimeline());

            System.out.println("Done ");
        }
    }

    private Matches validateSaveOrUpdate(Matches matches, ScoreTimeline scoreTimeline){

        Matches match = appServices.findMatch(matches.getTeamA(), matches.getTeamB(), matches.getMatchDescription());
        if (match == null) {
            System.out.println("description length: "+matches.getDescription().length());
            matches.setMatchDate(new Date());
            matches.setUpdateTime(new Date());
            return appServices.saveMatch(matches);
        }
        boolean update= false;
        boolean scoreChanged = false;

        //
        if(!matches.getMatchStatus().equals(match.getMatchStatus())){
            match.setMatchStatus(matches.getMatchStatus());
            update=true;
        }

        // If any of the result is published

        match.setTeamAMatchResult(matches.getTeamAMatchResult());
        match.setTeamBMatchResult(matches.getTeamBMatchResult());
        // If batting or not
        match.setTeamABatting(matches.getTeamABatting());
        match.setTeamBBatting(matches.getTeamBBatting());

        // If any change in score-info
        if(!matches.getTeamAScoreInfo().equals(match.getTeamAScoreInfo()) && !matches.getTeamAScoreInfo().equals("")){
            match.setTeamAScoreInfo(matches.getTeamAScoreInfo());
            update=true;
            scoreChanged=true;
        }

        if(!matches.getTeamBScoreInfo().equals(match.getTeamBScoreInfo()) && !matches.getTeamBScoreInfo().equals("")){
            match.setTeamBScoreInfo(matches.getTeamBScoreInfo());
            update=true;
            scoreChanged=true;
        }

        // If any change in score
        if(!matches.getTeamAScore().equals(match.getTeamAScore()) && !matches.getTeamAScore().equals("")){
            match.setTeamAScore(matches.getTeamAScore());
            update=true;
        }

        if(!matches.getTeamBScore().equals(match.getTeamBScore()) && !matches.getTeamBScore().equals("")){
            match.setTeamBScore(matches.getTeamBScore());
            update=true;
        }

        //if ((match.getDescription() == null || match.getDescription().isEmpty() || !match.getDescription().equals(matches.getDescription())) && !matches.getDescription().isEmpty()){
        System.out.println("check description");
        if (!match.getDescription().equals(matches.getDescription())){
            match.setDescription(matches.getDescription());
            update=true;
        }


        if (!match.getTossStat().equals(matches.getTossStat())) {
            match.setTossStat(matches.getTossStat());
            update=true;
        }

        if (!matches.getLiveStat().equals(match.getLiveStat())){
            match.setLiveStat(matches.getLiveStat());
            update=true;
        }

        //update=true;
        if(update){
            System.out.println("Updating Match: "+match.toString());
            match.setUpdateTime(new Date());
            match = appServices.saveMatch(match);
            if(scoreChanged){
                scoreTimeline.setMatchId(match);
                scoreTimeline = appServices.saveScoreTimeLine(scoreTimeline);
            }
        }

        return match;
    }
}
