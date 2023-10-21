package objectpool;

import user.*;
import factory.*;

import java.util.ArrayList;
import java.util.List;

public class UserPoolImpl implements UserPool{

    private final List<User> userPool;
    private final int maxSize;

    public UserPoolImpl(int maxSize) {
        this.maxSize = maxSize;
        this.userPool = new ArrayList<>(maxSize);
    }

    public synchronized User acquireUser(String userId, String username, String password, boolean isAdmin) {
        if (userPool.isEmpty()) {
            return createUser(userId, username, password, isAdmin);
        } else {
            User user = userPool.remove(0);
            user.setUserId(userId);
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
    }

    public synchronized void releaseUser(User user) {
        if (user == null){
            return;
        }

        if (userPool.size() < maxSize) {
            userPool.add(user);
        }
    }

    private User createUser(String userId, String username, String password, boolean isAdmin) {
        if (isAdmin) {
            return UserFactory.getUser(userId, username, password, UserType.ADMIN_USER);
        } else {
            return UserFactory.getUser(userId, username, password, UserType.REGULAR_USER);
        }
    }
}
