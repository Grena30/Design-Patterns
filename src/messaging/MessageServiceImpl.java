package messaging;

import user.RegularUser;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService{

    private MessageStorage messageStorage;

    public MessageServiceImpl(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void sendMessage(RegularUser sender, RegularUser receiver, Message message) {
        if (sender != null && receiver != null && message != null) {
            messageStorage.saveMessage(message);
        }
    }

    @Override
    public List<Message> getMessages(RegularUser user) {
        if (user != null) {
            return messageStorage.getMessagesByUser(user.getUserId());
        }
        return new ArrayList<>();
    }
}
