package messaging;

import user.RegularUser;

import java.util.List;

public interface MessageService {
    void sendMessage(RegularUser sender, RegularUser receiver, Message message);
    List<Message> getMessages(RegularUser user);
}
