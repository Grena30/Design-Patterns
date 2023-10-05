package user;

public class RegularUser extends User{

    public RegularUser(String userId, String username, String password) {
        super(userId, username, password);
    }

    @Override
    public UserType getUserType() {
        return UserType.REGULAR_USER;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("User ID: " + getUserId());
        System.out.println("Username: " + getUsername());
    }
}
