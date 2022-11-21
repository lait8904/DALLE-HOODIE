package main.java.dalleHoodie.services;

import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;
import main.java.dalleHoodie.model.User;
import main.java.dalleHoodie.repository.UsersRepository;

public class LoginService implements IService {

    private ApplicationContext applicationContext;
    private UsersRepository usersRepository;
    public LoginService(ApplicationContext applicationContext,
                           UsersRepository usersRepository) {
        this.applicationContext = applicationContext;
        this.usersRepository = usersRepository;
    }
    @Override
    public String perform(String[] param) {
        if (param.length != 2)
            return "Error (Number of parameters)\n";
        User user = usersRepository.findByLogin(param[0]);
        if (user == null)
            return "No user with such login\n";
        if (user.getPassword().equals(param[1])) {
            applicationContext.setUser(user);
            return "Success!\n";
        }
        return "Invalid password\n";
    }
}
