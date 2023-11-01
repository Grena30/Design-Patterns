package facade;

import auth.UserAuthenticationService;
import decorator.MessageEncryptionDecorator;
import decorator.MessageServiceDecorator;
import iterator.MessageIterator;
import iterator.MessageIteratorImpl;
import management.UserManagementService;
import messaging.Message;
import messaging.MessageService;
import messaging.MessageServiceImpl;
import messaging.MessageStorage;
import strategy.EncryptionStrategy;
import user.User;

import java.util.List;

public class MessageCommunicationFacade {
    private final MessageServiceDecorator messageServiceDecorator;

    private final UserManagementService userManagementService;

    private final UserAuthenticationService userAuthenticationService;

    public MessageCommunicationFacade(MessageStorage messageStorage,
                                      UserManagementService userManagementService,
                                      UserAuthenticationService userAuthenticationService,
                                      EncryptionStrategy encryptionStrategy) {
        MessageService messageService = new MessageServiceImpl(messageStorage);
        this.messageServiceDecorator = new MessageEncryptionDecorator(messageService, encryptionStrategy);
        this.userManagementService = userManagementService;
        this.userAuthenticationService = userAuthenticationService;
    }

    public void sendMessage(User sender, User receiver, Message message) {
        if (sender == null || receiver == null || message == null) {
            throw new IllegalArgumentException("Sender, receiver and message cannot be null");
        } else if (message.getMessageData() == null || message.getMessageData().isEmpty()) {
            throw new IllegalArgumentException("Message data cannot be null or empty");
        } else {
            messageServiceDecorator.sendMessage(sender, receiver, message);
            System.out.println("Message sent.");
        }
    }

    public void receiveMessageUser(User user, String secretKey) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        System.out.println("Messages for " + user.getUsername() + ":");
        List<Message> messageList = messageServiceDecorator.getMessages(user);
        if (secretKey == null || secretKey.isEmpty()) {
            printMessage(userManagementService, messageList);
            return;
        }

        User loggedInUser = userAuthenticationService.login(user.getUsername(), secretKey);

        if (loggedInUser != null) {
            messageList = messageServiceDecorator.sendUserMessages(messageList, loggedInUser);
            printMessage(userManagementService, messageList);
        } else {
            printMessage(userManagementService, messageList);
        }
    }

    public void viewMessageAdmin(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        System.out.println("Messages for " + user.getUsername() + ":");
        List<Message> messageList = messageServiceDecorator.sendUserMessages(messageServiceDecorator.getMessages(user), user);
        printMessage(userManagementService, messageList);
    }

    private void printMessage(UserManagementService userService, List<Message> messages) {
        MessageIterator messageIterator = getMessageIterator(messages);

        System.out.println("Iterating over messages...");
        while(messageIterator.hasNext()) {
            Message msg = messageIterator.getNext();
            User senderUser = userService.getUserById(msg.getSenderId());

            if (senderUser != null) {
                System.out.println("From: " + senderUser.getUsername() + ", Message: " + msg.getMessageData() +
                        ", Type: " + msg.getMessageType() + ", Date: " + msg.getDate() + ", Status: " + msg.getMessageStatus() + ", ID: " + msg.getMessageId());
            } else {
                System.out.println("From: Nonexistent user, Message: " + msg.getMessageData() +
                        ", Type: " + msg.getMessageType() + ", Date: " + msg.getDate() + ", Status: " + msg.getMessageStatus() + ", ID: " + msg.getMessageId());
            }
        }
    }

    private MessageIterator getMessageIterator(List<Message> messages) {
        return new MessageIteratorImpl(messages);
    }
}