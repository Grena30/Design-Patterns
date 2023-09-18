package builder;

import messaging.Message;
import messaging.MessageStatus;
import messaging.MessageType;

import java.util.Date;

public interface MessageBuilder {
    MessageBuilder messageId(String messageId);
    MessageBuilder senderId(String senderId);
    MessageBuilder receiverId(String receiverId);
    MessageBuilder messageData(String messageData);
    MessageBuilder messageType(MessageType messageType);
    MessageBuilder date(Date date);
    MessageBuilder messageStatus(MessageStatus messageStatus);
    Message build();
}
