package user;

public class User {

    private String userId;
    private String username;

    private UserProfile profile;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.profile = new UserProfile();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void updateProfile(String name, String profilePictureUrl) {
        this.profile.updateProfile(name, profilePictureUrl);
    }

}
