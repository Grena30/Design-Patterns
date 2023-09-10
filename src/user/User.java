package user;

public class User {

    private String userId;
    private String username;

    private String password;
    private UserProfile profile;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.profile = new UserProfile();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void updateProfile(UserProfile profileData) {
        this.profile.updateProfile(profileData);
    }

}
