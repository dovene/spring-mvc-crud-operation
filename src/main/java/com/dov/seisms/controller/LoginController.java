package com.dov.seisms.controller;

import com.dov.seisms.model.User;
import com.dov.seisms.repository.SurveyRepository;
import com.dov.seisms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value={"/", "login"})
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String login(HttpSession session, @ModelAttribute User userParam) {
     boolean isUserAllowed = userRepository.getFakeUsers().stream().filter(new Predicate<User>() {
         @Override
         public boolean test(User user) {
             return user.areEqual(userParam);
         }
     }).collect(Collectors.toList()).size()>0;
        if(isUserAllowed){
            session.setAttribute("user", userParam);
            return "redirect:/surveys";
        } else {
            return "redirect:/accessDenied";
        }
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "accessDenied";
    }
}
