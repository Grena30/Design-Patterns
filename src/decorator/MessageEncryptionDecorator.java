package decorator;

import messaging.*;
import strategy.EncryptionStrategy;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class MessageEncryptionDecorator extends MessageServiceDecorator {

    private final EncryptionStrategy encryptionStrategy;

    public MessageEncryptionDecorator(MessageService messageService, EncryptionStrategy encryptionStrategy) {
        super(messageService);
        this.encryptionStrategy = encryptionStrategy;
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
        message.setMessageData(encrypt(message, receiver.getUsername(), encryptionStrategy));
        super.sendMessage(sender, receiver, message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return super.getMessages(user);
    }

    @Override
    public List<Message> sendUserMessages(List<Message> messages, User user) {
        List<Message> userMessages = new ArrayList<>();
        for (Message message : messages) {
            Message newMessage = new MessageData(message.getMessageId(), message.getSenderId(), message.getReceiverId(),
                    decrypt(message, user.getUsername(), encryptionStrategy), message.getMessageType(), message.getDate(), message.getMessageStatus());
            userMessages.add(newMessage);

        }
        return super.sendUserMessages(userMessages, user);
    }

    private String encrypt(Message message, String username, EncryptionStrategy encryptionStrategy) {
        return encryptionStrategy.encrypt(message.getMessageData(), username);
    }

    private String decrypt(Message message, String username, EncryptionStrategy encryptionStrategy) {
        return encryptionStrategy.decrypt(message.getMessageData(), username);
    }

}
