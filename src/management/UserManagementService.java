package management;

import user.RegularUser;
import user.User;
import user.UserProfile;

import java.util.List;

public interface UserManagementService {

    User getUserById(String userId);
    List<User> getUserList();
    void deleteUser(String userId);
    void changeUserName(String userId, String newUsername);
    void changeUserPassword(String userId, String newPassword);

}
