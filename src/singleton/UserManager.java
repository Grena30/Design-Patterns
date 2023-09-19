package singleton;

import user.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private final Map<String, User> users;

    private UserManager() {
        users = new HashMap<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
