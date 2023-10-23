package management;

import objectpool.UserPool;
import singleton.UserManager;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagementServiceImpl implements UserManagementService, UserAdditionService{

        private final UserManager userManager;
        private final UserPool userPool;

        public UserManagementServiceImpl(UserPool userPool, UserManager userManager) {
                this.userPool = userPool;
                this.userManager = userManager;
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
        public void updateUser(String userId, String newUsername, String newPassword) {
                User user = getUserById(userId);
                if (newUsername != null && !newUsername.isEmpty()) {
                        userManager.getUsers().remove(user.getUsername());
                        user.setUsername(newUsername);
                        userManager.getUsers().put(newUsername, user);
                }

                if (newPassword != null && !newPassword.isEmpty()) {
                        user.setPassword(newPassword);
                }
        }

        @Override
        public void addUser(String username, String password, boolean isAdmin) {
                // TODO: Change this to use a unique userId
                String userId = "1";
                User user = userPool.acquireUser(userId, username, password, isAdmin);

                userManager.getUsers().put(username, user);
        }

}
