package com.palash.webXGlobal.controllers;

import com.palash.webXGlobal.entities.Matches;
import com.palash.webXGlobal.entities.ScoreTimeline;
import com.palash.webXGlobal.entities.Users;
import com.palash.webXGlobal.services.AppServices;
import com.palash.webXGlobal.specifications.ScoreTimeLineSpecs;
import com.palash.webXGlobal.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.lang.model.util.Elements;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AppController {

    private static AppServices appServices;
    private static HelperFunctions helperFunctions;

    @Autowired
    AppController(AppServices appServices){
        this.appServices = appServices;
        this.helperFunctions = new HelperFunctions(this.appServices);
    }

    @GetMapping(value = {"","/","/login"})
    public String loginPage(Model model, Principal principal) {
        System.out.println("in login...");

        //System.out.println("in login..."+appServices.findByUsername("maestro"));

        if(principal != null)
            return "redirect:/welcome";


        return "login";

    }


    @GetMapping(value = {"/welcome"})
    public String welcomePage( Model model, Principal principal, HttpSession session) {
        //List<Users> users = userServices.getUsers();
        System.out.println("user from principal : "+principal.getName());
        Users currentUser = appServices.findByUsername(principal.getName());
        helperFunctions.checkSession(principal, session);
        model.addAttribute("CurrentUser", currentUser);
        session.setAttribute("CurrentUser", currentUser);
        //Users user = userServices.getUserByUsername(principal.getName());
        System.out.println("Welcome ");

        List<Matches> matches = appServices.findMatches();
        System.out.println("matches size: "+matches.size());
        model.addAttribute("matches", matches);

        List<Matches> finishedMatches = appServices.findFinishedMatches();
        System.out.println("finishedMatches: "+finishedMatches.size());
        model.addAttribute("finishedMatches", finishedMatches);

        return "welcome";

    }

    @GetMapping("/createUser")
    public String createUser(Model model, Principal principal, HttpSession session){
        model.addAttribute("users", new Users());

        return "create-user";
    }

    @PostMapping("/createUser")
    public String saveUser(@ModelAttribute Users users, Model model, RedirectAttributes redirectAttributes){

        users.setPassword(helperFunctions.passwordEncoder().encode(users.getPassword()));
        System.out.println("Users: "+users.toString());

        //users = appServices.saveUser(users);


        return "create-user";
    }

    @GetMapping("/searchMatch")
    @ResponseBody
    public Object searchMatch(@RequestParam("term") String team_name, Principal principal){

        if (principal == null){
            return "{\"status\":440,\"msg\":\"Session timeout, Please login to continue\"}";
        }

        List<Map<String, String>> array = appServices.findTeamMatchList(team_name);

        return array;
    }

    @PostMapping("/matchDetails")
    public String matchDetails(@RequestParam("c-search") Long id, Model model){

        return showMatchDetails(id, model);
    }

    @PostMapping("/matchDetails/{id}")
    public String matchDetailsById(@PathVariable("id") Long id, Model model){

        return showMatchDetails(id, model);
    }

    @GetMapping("/matchDetails/{id}")
    public String matchDetailsByIds(@PathVariable("id") Long id, Model model, Principal principal){

        if (principal == null)
            return "redirect:/logout";
        return showMatchDetails(id, model);
    }

    private String showMatchDetails(Long id, Model model){
        System.out.println("matchDetails: "+id);
        Matches match = appServices.findMatchById(id);
        model.addAttribute("match", match);
        List<Matches> matches = appServices.findMatches();
        model.addAttribute("matches", matches);
        List<Matches> finishedMatches = appServices.findFinishedMatches();
        System.out.println("finishedMatches: "+finishedMatches.size());
        model.addAttribute("finishedMatches", finishedMatches);
        return "matchDetails";

    }

    @GetMapping("/listScore")
    public @ResponseBody
    DataTablesOutput<ScoreTimeline> listEmployees(@RequestParam("matchId") Long matchId, @Valid DataTablesInput input,
                                             Principal principal, HttpSession session){

        Specification<ScoreTimeline> ScoreTimeline_specifications = ScoreTimeLineSpecs.allScoresByMatch(matchId);
        return appServices.findAllScoresForMatch(input, ScoreTimeline_specifications);
    }


}
