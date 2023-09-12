package user;

public class UserProfile {

    private String name;
    private String profilePictureUrl;

    public String getName() {
        return name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void updateProfile(UserProfile profileData) {
        if (profileData.getName() != null) {
            this.name = profileData.getName();
        }
        if (profileData.getProfilePictureUrl() != null) {
            this.profilePictureUrl = profileData.getProfilePictureUrl();
        }
    }
}
