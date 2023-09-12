package singleton;

import user.RegularUser;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private Map<String, RegularUser> users;

    private UserManager() {
        users = new HashMap<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public Map<String, RegularUser> getUsers() {
        return users;
    }

    public void addUser(RegularUser user) {
        users.put(user.getUsername(), user);
    }
}
