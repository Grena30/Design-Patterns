package auth;

import user.User;

public interface UserAuthenticationService {
    User registerUser(String username, String password);
    User login(String username, String password);
    void logout(User user);
}
