package com.dov.seisms.controller;


import com.dov.seisms.model.Survey;
import com.dov.seisms.repository.SurveyJpaRepository;
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
    SurveyJpaRepository surveyJpaRepository;

    @GetMapping
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/accessDenied";
        }
        model.addAttribute("surveys", surveyJpaRepository.findAll());
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
        surveyJpaRepository.save(survey);
        return "redirect:/surveys";
    }

    // Edit a survey
    @GetMapping
    @RequestMapping("/edit/{name}")
    public String edit(Model model, @PathVariable("name") String name) {
        model.addAttribute("survey", surveyJpaRepository.findOne(name));
        return "editSurvey";
    }

    @PostMapping("edit/{name}")
    public String editSave(@ModelAttribute Survey survey) {
        surveyJpaRepository.save(survey);
        return "redirect:/surveys";
    }

    // Delete a  survey
    @GetMapping
    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("name") String name) {
        surveyJpaRepository.delete(name);
        return "redirect:/surveys";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
