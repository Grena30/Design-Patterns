package observer;

public class ChatRoom {

    public EventManager events;

    public ChatRoom() {
        this.events = new EventManager("log-in", "log-out");
    }

    public void login(String username) {
        events.notify("log-in", username);
    }

    public void logout(String username) {
        events.notify("log-out", username);
    }
}
