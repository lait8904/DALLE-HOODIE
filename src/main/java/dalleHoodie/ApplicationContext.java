package dalleHoodie;

import dalleHoodie.model.User;

public class ApplicationContext {
    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
