package auth;

import user.User;

import java.util.*;

public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private Map<String, User> users = new HashMap<>();
    private Set<String> loggedInUsers = new HashSet<>();
    private int userIdCounter = 0;

    @Override
    public User registerUser(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        String userId = generateUniqueUserId();
        User user = new User(userId, username, password);
        users.put(username, user);
        return user;
    }

    private String generateUniqueUserId() {
        String userId = "user_" + userIdCounter;
        userIdCounter++;
        return userId;
    }

    @Override
    public User login(String username, String password) {
        User user = users.get(username);
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

    public List<User> getLoggedInUsers() {
        List<User> loggedInUserList = new ArrayList<>();
        for (String username : loggedInUsers) {
            User user = users.get(username);
            if (user != null) {
                loggedInUserList.add(user);
            }
        }
        return loggedInUserList;
    }
}
