package com.dov.seisms.repository;

import com.dov.seisms.model.Survey;
import com.dov.seisms.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {


    private List<User> fakeUsers = Arrays.asList( new User("test","pass"), new User("admin","admin"));

    public List<User> getFakeUsers() {
        return fakeUsers;
    }
}
