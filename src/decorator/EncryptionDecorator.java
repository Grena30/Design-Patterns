package decorator;

import messaging.*;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class EncryptionDecorator extends MessageServiceDecorator{

    public EncryptionDecorator(MessageService messageService) {
        super(messageService);
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
        message.setMessageData(encrypt(message, 15));
        super.sendMessage(sender, receiver, message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return super.getMessages(user);
    }

    @Override
    public List<Message> sendUserMessages(List<Message> messages) {
        List<Message> userMessages = new ArrayList<>();
        for (Message message : messages) {
            Message newMessage = new MessageData(message.getMessageId(), message.getSenderId(), message.getReceiverId(),
                    decrypt(message, 15), message.getMessageType(), message.getDate(), message.getMessageStatus());
            userMessages.add(newMessage);

        }
        return super.sendUserMessages(userMessages);
    }

    private String encrypt(Message message, int shift) {
        String messageData = message.getMessageData();
        StringBuilder encryptedData = new StringBuilder();

        for (int i = 0; i < messageData.length(); i++) {
            char currentChar = messageData.charAt(i);

            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) (currentChar + shift);

                if ((Character.isLowerCase(currentChar) && shiftedChar > 'z') ||
                        (Character.isUpperCase(currentChar) && shiftedChar > 'Z')) {
                    shiftedChar = (char) (currentChar - (26 - shift));
                }

                encryptedData.append(shiftedChar);
            } else {
                encryptedData.append(currentChar);
            }
        }

        return encryptedData.toString();
    }

    private String decrypt(Message message, int shift) {
        String encryptedData = message.getMessageData();
        StringBuilder decryptedData = new StringBuilder();

        for (int i = 0; i < encryptedData.length(); i++) {
            char currentChar = encryptedData.charAt(i);

            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) (currentChar - shift);

                if ((Character.isLowerCase(currentChar) && shiftedChar < 'a') ||
                        (Character.isUpperCase(currentChar) && shiftedChar < 'A')) {
                    shiftedChar = (char) (currentChar + (26 - shift));
                }

                decryptedData.append(shiftedChar);
            } else {
                decryptedData.append(currentChar);
            }
        }

        return decryptedData.toString();
    }

}
