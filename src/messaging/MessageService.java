package messaging;

import user.User;

import java.util.List;

public interface MessageService {
    void sendMessage(User sender, User receiver, Message message);
    List<Message> getMessages(User user);
}
