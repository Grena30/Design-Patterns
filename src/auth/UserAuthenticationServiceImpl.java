package auth;

import singleton.UserManager;
import user.RegularUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private UserManager userManager = UserManager.getInstance();
    private Set<String> loggedInUsers = new HashSet<>();
    private int userIdCounter = 0;

    @Override
    public RegularUser registerUser(String username, String password) {
        if (userManager.getUsers().containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        String userId = generateUniqueUserId();
        RegularUser user = new RegularUser(userId, username, password);
        userManager.addUser(user);
        return user;
    }

    private String generateUniqueUserId() {
        String userId = "user_" + userIdCounter;
        userIdCounter++;
        return userId;
    }

    @Override
    public RegularUser login(String username, String password) {
        RegularUser user = userManager.getUsers().get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUsers.add(username);
            return user;
        }
        return null;
    }

    @Override
    public void logout(RegularUser user) {
        loggedInUsers.remove(user.getUsername());
    }

    public List<RegularUser> getLoggedInUsers() {
        List<RegularUser> loggedInUserList = new ArrayList<>();
        for (String username : loggedInUsers) {
            RegularUser user = userManager.getUsers().get(username);
            if (user != null) {
                loggedInUserList.add(user);
            }
        }
        return loggedInUserList;
    }
}
