package messaging;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService{

    private MessageStorage messageStorage;

    public MessageServiceImpl(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
    }

    @Override
    public List<Message> getMessages(User user, User otherUser) {
        return new ArrayList<>();
    }

}
