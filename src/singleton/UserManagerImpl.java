package singleton;

import user.User;

import java.util.HashMap;
import java.util.Map;

public class UserManagerImpl implements UserManager{
    private static UserManagerImpl instance;
    private final Map<String, User> users;

    private UserManagerImpl() {
        users = new HashMap<>();
    }

    public static synchronized UserManagerImpl getInstance() {
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
