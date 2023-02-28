package dalleHoodie.services;

import dalleHoodie.model.User;
import dalleHoodie.repository.UsersRepository;
import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;

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
        User user = usersRepository.createUser(param[0], param[1],
                null, null, null);

        if (user != null)
            return "Success!\n";
        return "User with this login already exists\n";
    }
}
