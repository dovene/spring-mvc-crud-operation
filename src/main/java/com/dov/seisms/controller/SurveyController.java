package com.dov.seisms.controller;


import com.dov.seisms.model.Survey;
import com.dov.seisms.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/accessDenied";
        }
        model.addAttribute("surveys", surveyRepository.getSurveys());
        return "surveys";
    }

    // Add a new survey
    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("survey", new Survey());
        return "addSurvey";
    }

    @PostMapping("/create")
    public String addSave(@ModelAttribute Survey survey) {
        surveyRepository.getSurveys().add(survey);
        return "redirect:/surveys";
    }

    // Edit a survey
    @GetMapping
    @RequestMapping("/edit/{name}")
    public String edit(Model model, @PathVariable("name") String name) {
        model.addAttribute("survey", surveyRepository.getSurveys().stream().filter(new Predicate<Survey>() {
            @Override
            public boolean test(Survey survey) {
                return survey.getName().equals(name);
            }
        }).collect(Collectors.toList()).get(0));
        return "editSurvey";
    }

    @PostMapping("edit/{name}")
    public String editSave(@ModelAttribute Survey survey) {
        Survey previousSurvey = surveyRepository.getSurveys().stream().filter(survey1 -> survey1.getName().equals(survey.getName())).collect(Collectors.toList()).get(0);
        int previousSurveyIndex = surveyRepository.getSurveys().indexOf(previousSurvey);
        surveyRepository.getSurveys().set(previousSurveyIndex, survey);
        return "redirect:/surveys";
    }

    // Delete a  survey
    @GetMapping
    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("name") String name) {
        surveyRepository.getSurveys().remove(surveyRepository.getSurveys().stream().filter(survey -> survey.getName().equals(name)).collect(Collectors.toList()).get(0));
        return "redirect:/surveys";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
