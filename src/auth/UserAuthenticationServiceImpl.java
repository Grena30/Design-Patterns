package auth;

import singleton.UserManager;
import user.RegularUser;
import user.User;
import factory.*;
import user.UserType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private final UserManager userManager = UserManager.getInstance();
    private Set<String> loggedInUsers = new HashSet<>();
    private int userIdCounter = 0;
    private final UserFactory regularFactory = new RegularUserFactory();
    private final UserFactory adminFactory = new AdminFactory();


    @Override
    public User registerUser(String username, String password, boolean isAdmin) {
        if (userManager.getUsers().containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        String userId = generateUniqueUserId();
        User user;

        if (isAdmin) {
            user = adminFactory.createUser(userId, username, password);
        } else {
            user = regularFactory.createUser(userId, username, password);
        }

        userManager.addUser(user);
        return user;
    }

    private String generateUniqueUserId() {
        String userId = "user_" + userIdCounter;
        userIdCounter++;
        return userId;
    }

    @Override
    public User login(String username, String password) {
        User user = userManager.getUsers().get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUsers.add(username);
            return user;
        }
        return null;
    }

    @Override
    public void logout(User user) {
        loggedInUsers.remove(user.getUsername());
    }

    public List<User> getLoggedInUsers(boolean isAdmin) {
        List<User> loggedInUserList = new ArrayList<>();
        for (String username : loggedInUsers) {
            User user = userManager.getUsers().get(username);
            if (user != null) {
                if (isAdmin && user.getUserType() == UserType.REGULAR_USER) {
                    continue;
                } else if (!isAdmin && user.getUserType() == UserType.ADMIN_USER){
                    continue;
                }
                loggedInUserList.add(user);
            }
        }
        return loggedInUserList;
    }
}
