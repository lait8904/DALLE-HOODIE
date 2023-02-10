package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;
import dalleHoodie.model.User;
import dalleHoodie.repository.UsersRepository;

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
