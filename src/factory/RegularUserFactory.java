package factory;

import user.AdminUser;
import user.RegularUser;
import user.User;

public class RegularUserFactory implements UserFactory {
    public User createUser(String userId, String username, String password) {
        return new RegularUser(userId, username, password);
    }
}
