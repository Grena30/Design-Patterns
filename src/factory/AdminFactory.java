package factory;

import user.AdminUser;
import user.User;

public class AdminFactory implements UserFactory {
    public User createUser(String userId, String username, String password) {
        return new AdminUser(userId, username, password);
    }
}
