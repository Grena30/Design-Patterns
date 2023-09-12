package user;

public class AdminUser extends User {
    private boolean adminStatus;

    public AdminUser(String userId, String username, String password) {
        super(userId, username, password);
        this.adminStatus = true;
    }

    public boolean isAdmin() {
        return adminStatus;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Admin ID: " + getUserId());
        System.out.println("Username: " + getUsername());
        System.out.println("Admin Status: " + isAdmin());
    }
}
