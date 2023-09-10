package management;

import user.User;
import user.UserProfile;

import java.util.List;

public interface UserManagementService {

    public void addUser(User user);
    User getUserById(String userId);
    void updateUserProfile(User user, UserProfile profileData);
    List<User> getUserList();
}
