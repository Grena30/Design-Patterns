package builder;

import messaging.Message;
import messaging.MessageStatus;
import messaging.MessageType;

import java.util.Date;

public interface MessageBuilder {
    void setMessageId(String messageId);
    void setSenderId(String senderId);
    void setReceiverId(String receiverId);
    void setMessageData(String messageData);
    void setMessageType(MessageType messageType);
    void setDate(Date date);
    void setMessageStatus(MessageStatus messageStatus);
    Message build();
}
