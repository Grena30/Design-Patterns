package management;

import user.RegularUser;
import user.UserProfile;

import java.util.List;

public interface UserManagementService {

    RegularUser getUserById(String userId);
    void updateUserProfile(RegularUser user, UserProfile profileData);
    List<RegularUser> getUserList();

}
