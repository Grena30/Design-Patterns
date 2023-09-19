package management;

import objectpool.UserPool;
import singleton.UserManager;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagementServiceImpl implements UserManagementService{

        private final UserManager userManager = UserManager.getInstance();
        private final UserPool userPool;

        public UserManagementServiceImpl(UserPool userPool) {
                this.userPool = userPool;
        }

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
        public List<User> getUserList() {
                return new ArrayList<>(userManager.getUsers().values());
        }

        @Override
        public void deleteUser(String userId) {
                userPool.releaseUser(getUserById(userId));
                userManager.getUsers().remove(getUserById(userId).getUsername());

        }

        @Override
        public void changeUserName(String userId, String newUsername) {
                if (newUsername != null && !newUsername.isEmpty()) {
                        User user = getUserById(userId);
                        user.setUsername(newUsername);
                        userManager.getUsers().put(newUsername, user);
                }
        }

        @Override
        public void changeUserPassword(String userId, String newPassword) {
                if (newPassword != null && !newPassword.isEmpty()) {
                        User user = getUserById(userId);
                        user.setPassword(newPassword);
                }
        }

}
