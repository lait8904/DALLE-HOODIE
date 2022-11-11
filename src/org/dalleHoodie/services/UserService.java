package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;
import org.dalleHoodie.model.User;
import org.dalleHoodie.repository.UserRepository;

import java.util.Arrays;

public class UserService implements IService {
    private ApplicationContext applicationContext;
    private UserRepository userRepository;

    public UserService(ApplicationContext applicationContext,
                           UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 1)
            return "Error (Number of arguments)\n";
        try {
            int userId = Integer.parseInt(param[0]);
            User user = userRepository.findById(userId);
            if (user == null)
                return "No such user\n";
            String out =    "Login: " + user.getLogin() + "\n" +
                            "Id: " + user.getUserId() + "\n" +
                            "First name: " + user.getFirstName() + "\n" +
                            "Last name: " + user.getLastName() + "\n" +
                            "E-mail: " + user.getEmail() + "\n" +
                            "Address: " + user.getAddress() + "\n";
            return out;
        } catch (NumberFormatException e) {
            return "Error (Unknown parameter)\n";
        }
    }
}
