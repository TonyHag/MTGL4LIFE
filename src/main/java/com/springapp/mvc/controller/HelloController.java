package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.model.leaderboard.Leaderboard;
import com.springapp.mvc.model.statistics.FFAStats;
import com.springapp.mvc.model.statistics.OneVsOneStats;
import com.springapp.mvc.model.statistics.THGStats;
import com.springapp.mvc.model.statistics.TotalStats;
import com.springapp.mvc.service.EncryptionService;
import com.springapp.mvc.service.IdService;
import com.springapp.mvc.service.MockDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Some users added");


        MockDB.leaderboards = new ArrayList<Leaderboard>();
        Leaderboard leaderboard =  new Leaderboard("worldLeaderboard");
        leaderboard.setId("worldLeaderboard");
        leaderboard.setName("World Wide");
        leaderboard.setDescription("Leaderboard containing all the players");

        MockDB.addLeaderboard(leaderboard);


        EncryptionService encryptionService = new EncryptionService();
        MockDB.users = new ArrayList<User>();
        User u1 = new User("Tony", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u2 = new User("Eirik", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u3 = new User("Hakon", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u4 = new User("Jonas", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u5 = new User("Gaute", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u6 = new User("Kim", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u7 = new User("Tor", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u8 = new User("Leiv", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u9 = new User("Fredrik", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");
        User u10 = new User("Oscar", encryptionService.encryptPassword("asdf"), "asdf", "Norway", "Bergen");


        System.out.println("HelloPage");


        return "hello";
	}
}