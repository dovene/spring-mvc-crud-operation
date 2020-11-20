package com.dov.seisms.repository;

import com.dov.seisms.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyJpaRepository  extends JpaRepository<Survey, String> {

}