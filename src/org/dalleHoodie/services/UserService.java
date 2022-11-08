package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;
import org.dalleHoodie.repository.UserRepository;

import java.util.Arrays;

public class UserService implements IService {
    private ApplicationContext applicationContext;
    private UserRepository userRepository;

    public UserService(ApplicationContext applicationContext,
                           UserRepository userRepository) {
        this.setApplicationContext(applicationContext);
        this.userRepository = userRepository;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String perform(String[] param) {
        if (param.length == 0)
            return "Error (Number of arguments)\n";
        String[] args = Arrays.copyOfRange(param, 1, param.length);
        switch(param[0]) {
            case "register":
                return doRegister(args);
            case "login":
                return doLogin(args);
            case "profile":
                return doProfile(args);
            default:
                return "Unknown command\n";
        }
    }

    private String doRegister(String[] param) {
        return null;
    }
    private String doLogin(String[] param) {
        return null;
    }
    private String doProfile(String[] param) {
        return null;
    }
}
