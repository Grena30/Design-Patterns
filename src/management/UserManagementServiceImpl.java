package management;

import singleton.UserManager;
import user.RegularUser;
import user.User;
import user.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UserManagementServiceImpl implements UserManagementService{

        private UserManager userManager = UserManager.getInstance();

        @Override
        public User getUserById(String userId) {
                for (User user : userManager.getUsers().values()) {
                        if (user.getUserId().equals(userId)) {
                                return user;
                        }
                }
                return null;
        }

        @Override
        public void updateUserProfile(RegularUser user, UserProfile profileData) {
                user.updateProfile(profileData);
        }

        @Override
        public List<User> getUserList() {
                return new ArrayList<>(userManager.getUsers().values());
        }

        public void deleteUser(String userId) {
                userManager.removeUser(getUserById(userId));
        }

}
