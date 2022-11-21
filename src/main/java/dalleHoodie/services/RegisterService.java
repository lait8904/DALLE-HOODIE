package main.java.dalleHoodie.services;

import main.java.dalleHoodie.repository.UsersRepository;
import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;

public class RegisterService implements IService {
    private ApplicationContext applicationContext;
    private UsersRepository usersRepository;
    public RegisterService(ApplicationContext applicationContext,
                           UsersRepository usersRepository) {
        this.applicationContext = applicationContext;
        this.usersRepository = usersRepository;
    }

    @Override
    //param: login, password
    public String perform(String[] param) {
        if (param.length != 2)
            return "Error (Number of parameters)\n";
        UsersRepository.Constants error = usersRepository.createUser(param[0], param[1],
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
