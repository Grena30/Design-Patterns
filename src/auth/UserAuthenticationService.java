package auth;

import user.RegularUser;

import java.util.List;

public interface UserAuthenticationService {
    RegularUser registerUser(String username, String password);
    RegularUser login(String username, String password);
    void logout(RegularUser user);
    List<RegularUser> getLoggedInUsers();
}
