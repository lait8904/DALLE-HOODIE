package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.Constants;
import org.dalleHoodie.IService;
import org.dalleHoodie.repository.UserRepository;

public class RegisterService implements IService {
    private ApplicationContext applicationContext;
    private UserRepository userRepository;
    public RegisterService(ApplicationContext applicationContext,
                           UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @Override
    //param: login, password
    public String perform(String[] param) {
        if (param.length != 2)
            return "Error (Number of parameters)\n";
        Constants error = userRepository.createUser(param[0], param[1],
                null, null, null, null);
        switch (error) {
            case SUCCESS:
                return "Success!\n";
            case SAME_LOGIN:
                return "User with this login already exists\n";
        }
        return null;
    }
}
