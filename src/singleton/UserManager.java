package singleton;

import user.User;

import java.util.Map;

public interface UserManager {
    Map<String, User> getUsers();
}
