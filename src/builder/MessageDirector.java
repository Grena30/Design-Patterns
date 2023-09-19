package builder;

import messaging.Message;
import messaging.MessageStatus;
import messaging.MessageType;

import java.util.Date;

public class MessageDirector {

    public Message constructTextMessage(MessageBuilder messageBuilder, String senderId, String receiverId, String messageData) {
        messageBuilder.setMessageId(String.valueOf(System.currentTimeMillis()));
        messageBuilder.setSenderId(senderId);
        messageBuilder.setReceiverId(receiverId);
        messageBuilder.setMessageData(messageData);
        messageBuilder.setMessageType(MessageType.TEXT);
        messageBuilder.setDate(new Date());
        messageBuilder.setMessageStatus(MessageStatus.SENT);
        return messageBuilder.build();
    }
}
