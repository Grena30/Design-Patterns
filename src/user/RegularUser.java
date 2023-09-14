package user;

public class RegularUser extends User{

    private UserProfile profile;

    public RegularUser(String userId, String username, String password) {
        super(userId, username, password);
        this.profile = new UserProfile();
    }

    public void updateProfile(UserProfile profileData) {
        this.profile.updateProfile(profileData);
    }

    public UserType getUserType() {
        return UserType.REGULAR_USER;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("User ID: " + getUserId());
        System.out.println("Username: " + getUsername());
    }
}
