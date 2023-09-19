package objectpool;

import user.*;
import factory.*;

import java.util.ArrayList;
import java.util.List;

public class UserPool {

    private final UserFactory regularFactory;
    private final UserFactory adminFactory;
    private final List<User> userPool;
    private final int maxSize;

    public UserPool(int maxSize, UserFactory regularFactory, UserFactory adminFactory) {
        this.regularFactory = regularFactory;
        this.adminFactory = adminFactory;
        this.maxSize = maxSize;
        this.userPool = new ArrayList<>(maxSize);
    }

    public synchronized User acquireUser(String userId, String username, String password, boolean isAdmin) {
        if (userPool.isEmpty()) {
            return createUser(userId, username, password, isAdmin);
        } else {
            // Reuse an existing User object from the pool
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
            // Return the User object to the pool if it's not full
            userPool.add(user);
        }
    }

    private User createUser(String userId, String username, String password, boolean isAdmin) {
        if (isAdmin) {
            return adminFactory.createUser(userId, username, password);
        } else {
            return regularFactory.createUser(userId, username, password);
        }
    }
}
