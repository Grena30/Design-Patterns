package messaging;

import java.util.Date;

public interface Message {
    String getMessageId();
    String getSenderId();
    String getReceiverId();
    String getMessageData();
    MessageType getMessageType();
    Date getDate();
    MessageStatus getMessageStatus();
}
