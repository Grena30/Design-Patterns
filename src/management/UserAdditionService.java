package management;

import user.User;

public interface UserAdditionService {

    void addUser(String username, String password, boolean isAdmin);
}
