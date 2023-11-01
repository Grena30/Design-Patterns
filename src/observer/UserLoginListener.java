package observer;

public class UserLoginListener implements EventListener {
    private final String user;

    public UserLoginListener(String user) {
        this.user = user;
    }
    @Override
    public void update(String evenType, String username) {
        System.out.println("To user: " + user + ". User " + username + " has performed a " + evenType);
    }

}
