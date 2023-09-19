package factory;

import user.User;

public interface UserFactory {
    User createUser(String userId, String username, String password);
}
