package user;

public class UserProfile {

    private String name;
    private String profilePictureUrl;

    public void updateProfile(String name, String profilePictureUrl) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
    }
}
