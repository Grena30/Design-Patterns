package messaging;

import java.util.List;

public interface MessageStorage {

    void saveMessage(Message message);
    List<Message> getMessagesByUser(String userId);
}
