package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;
import org.dalleHoodie.model.User;
import org.dalleHoodie.repository.UserRepository;

public class LoginService implements IService {

    private ApplicationContext applicationContext;
    private UserRepository userRepository;
    public LoginService(ApplicationContext applicationContext,
                           UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }
    @Override
    public String perform(String[] param) {
        if (param.length != 2)
            return "Error (Number of parameters)\n";
        User user = userRepository.findByLogin(param[0]);
        if (user == null)
            return "No user with such login";
        if (user.getPassword().equals(param[1])) {
            applicationContext.setUser(user);
            return "Success!\n";
        }
        return "Invalid password\n";
    }
}
