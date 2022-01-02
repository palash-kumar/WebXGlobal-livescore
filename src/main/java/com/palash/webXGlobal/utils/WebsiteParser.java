package com.palash.webXGlobal.utils;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WebsiteParser {

    public Document testParser(String url){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            System.out.println("URL: "+url);

            Document doc = Jsoup.connect(url).get();

            return doc;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
