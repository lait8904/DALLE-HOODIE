package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;
import dalleHoodie.model.User;
import dalleHoodie.repository.UsersRepository;

public class ProfileService implements IService {
    private ApplicationContext applicationContext;
    private UsersRepository usersRepository;

    public ProfileService(ApplicationContext applicationContext,
                          UsersRepository usersRepository) {
        this.applicationContext = applicationContext;
        this.usersRepository = usersRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 0)
            return "Error (Number of arguments)\n";
        User user = applicationContext.getUser();
        if (user != null) {
            String out =    "Email: " + user.getEmail() + "\n" +
                    "Id: " + user.getUserId() + "\n" +
                    "First name: " + user.getFirstName() + "\n" +
                    "Last name: " + user.getLastName() + "\n" +
                    "E-mail: " + user.getEmail() + "\n" +
                    "Address: " + user.getAddress() + "\n";
            return out;
        }
        return "Authorize firstly!\n";
    }
}
