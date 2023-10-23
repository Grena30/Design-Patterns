package auth;

import user.RegularUser;
import user.User;

import java.util.List;

public interface UserAuthenticationService {
    void registerUser(String username, String password, boolean isAdmin);
    User login(String username, String password);
    void logout(User user);
    List<User> getLoggedInUsers(boolean isAdmin);
}
