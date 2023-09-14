package factory;

import user.User;

public interface UserFactory {
    public User createUser(String userId, String username, String password);
}
