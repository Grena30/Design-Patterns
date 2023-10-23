package adapter;

import auth.UserAuthenticationService;
import management.UserAdditionService;
import user.User;
import user.UserType;


public class UserAuthenticationAdapter implements UserAdditionService {

    private final UserAuthenticationService userAuthenticationService;
    public UserAuthenticationAdapter(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public void addUser(String username, String password, boolean isAdmin) {
        userAuthenticationService.registerUser(username, password, isAdmin);
    }
}
