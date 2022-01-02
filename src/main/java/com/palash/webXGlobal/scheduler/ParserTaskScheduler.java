package com.palash.webXGlobal.scheduler;

import com.palash.webXGlobal.services.AppServices;
import com.palash.webXGlobal.utils.Channel;
import com.palash.webXGlobal.utils.HelperFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ParserTaskScheduler {

    private static final Logger log = LoggerFactory.getLogger(ParserTaskScheduler.class);
    private static AppServices appServices;
    private static HelperFunctions helperFunctions;;

    @Autowired
    ParserTaskScheduler(AppServices appServices){
        this.appServices = appServices;
        this.helperFunctions = new HelperFunctions(this.appServices);
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 300000)
    public void parseSiteFOrUpdate() {
        log.info("The time is now {}", new Date());
        Channel channel = helperFunctions.parseChannel();
    }
}
