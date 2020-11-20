package com.dov.seisms.repository;

import com.dov.seisms.model.Survey;
import com.dov.seisms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {

}