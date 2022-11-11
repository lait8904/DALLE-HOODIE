package org.dalleHoodie.repository;

import org.dalleHoodie.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.dalleHoodie.Constants;

public class UserRepository {

    public List<User> users = new ArrayList<>();

    public Constants createUser(String login,
                                String password,
                                String firstName,
                                String lastName,
                                String address,
                                String email) {

        for (User user : users)
            if (user.getLogin().equals(login))
                return Constants.SAME_LOGIN;
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
        return Constants.SUCCESS;
    }

    public User findById(int userId) {
        for (User user : users)
            if (user.getUserId() == userId)
                return user;
        return null;
    }

    public User findByLogin(String login) {
        for (User user : users)
            if (user.getLogin().equals(login)) {
                return user;
            }
        return null;
    }


}
