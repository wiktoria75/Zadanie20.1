package com.example.plwk;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();
        add(new User("Wiktoria", "Kulczyńska", 23));
        add(new User("Joanna", "Borkowska", 33));
        add(new User("Dominik", "Gąssowski", 34));
    }

    public List<User> getUserList() {
        return userList;
    }
    public void add(User user) {
        userList.add(user);
    }
}
