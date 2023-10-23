package management;

import user.User;

import java.util.List;

public interface UserManagementService {

    User getUserById(String userId);
    List<User> getUserList();
    void deleteUser(String userId);
    void updateUser(String userId, String newUsername, String newPassword);
}
