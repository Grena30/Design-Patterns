package decorator;

import messaging.Message;
import messaging.MessageService;
import user.User;

import java.util.List;

public class MessageServiceDecorator implements MessageService {

    private final MessageService messageService;

    public MessageServiceDecorator(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
        messageService.sendMessage(sender, receiver, message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return messageService.getMessages(user);
    }

    @Override
    public List<Message> sendUserMessages(List<Message> messages) {
        return messageService.sendUserMessages(messages);
    }
}
