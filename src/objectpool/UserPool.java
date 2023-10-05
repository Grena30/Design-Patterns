package objectpool;

import user.User;

public interface UserPool {
    User acquireUser(String userId, String username, String password, boolean isAdmin);
    void releaseUser(User user);
}
