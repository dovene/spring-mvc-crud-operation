package com.dov.seisms.repository;


import com.dov.seisms.model.Survey;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SurveyRepository {
    private ArrayList<Survey> fakeSurveys = new ArrayList<>();
    private ArrayList<Survey> surveys;

    public SurveyRepository() {
        surveys = fakeSurveys;
    }

    public ArrayList<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(ArrayList<Survey> surveys) {
        this.surveys = surveys;
    }
}
