package management;

import user.RegularUser;
import user.User;
import user.UserProfile;

import java.util.List;

public interface UserManagementService {

    User getUserById(String userId);
    void updateUserProfile(RegularUser user, UserProfile profileData);
    List<User> getUserList();
    void deleteUser(String userId);

}
