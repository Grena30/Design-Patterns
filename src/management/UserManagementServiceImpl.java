package management;

import user.User;
import user.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagementServiceImpl implements UserManagementService{

        private Map<String, User> users = new HashMap<>();

        public void addUser(User user) {
                users.put(user.getUserId(), user);
        }
        @Override
        public User getUserById(String userId) {
                return users.get(userId);
        }

        @Override
        public void updateUserProfile(User user, UserProfile profileData) {
                user.updateProfile(profileData);
        }

        @Override
        public List<User> getUserList() {
                return new ArrayList<>(users.values());
        }
}
