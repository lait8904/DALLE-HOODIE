package org.dalleHoodie.repository;

import org.dalleHoodie.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final int SAME_LOGIN = 1;

    public List<User> users = new ArrayList<>();

    public int createUser(String login,
                    String firstName,
                    String lastName,
                    String address,
                    String password,
                    String email) {

        for (User user : users)
            if (user.getLogin().equals(login))
                return SAME_LOGIN;
        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        user.setUserId(users.size());
        users.add(user);
        return 0;
    }

    public User getUser(int userId) {
        for (User user : users)
            if (user.getUserId() == userId)
                return user;
        return null;
    }


}
