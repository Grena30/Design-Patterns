package user;

public class AdminUser extends User {

    public AdminUser(String userId, String username, String password) {
        super(userId, username, password);
    }

    public UserType getUserType() {
        return UserType.ADMIN_USER;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Admin ID: " + getUserId());
        System.out.println("Username: " + getUsername());
    }
}
