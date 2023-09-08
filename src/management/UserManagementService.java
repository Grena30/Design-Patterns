package management;

import user.User;
import user.UserProfile;

import java.util.List;

public interface UserManagementService {

    User getUserById(String userId);
    void updateUserProfile(User user, UserProfile profileData);
    List<User> getUserList();
}
