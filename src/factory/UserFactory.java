package factory;

import user.AdminUser;
import user.RegularUser;
import user.User;
import user.UserType;

import java.util.HashMap;
import java.util.Map;

public class UserFactory {
    private static final Map<String, User> userCache = new HashMap<>();

    public static User getUser(String userId, String username, String password, UserType userType) {
        String key = username + userType.toString();

        if (userCache.containsKey(key)) {
            return userCache.get(key);
        }

        User user;
        if (userType == UserType.REGULAR_USER) {
            user = new RegularUser(userId, username, password);
        } else {
            user = new AdminUser(userId, username, password);
        }

        userCache.put(key, user);
        return user;
    }
}


