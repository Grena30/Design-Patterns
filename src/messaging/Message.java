package messaging;

import java.util.Date;
import java.util.List;

public interface Message {
    String getMessageId();
    String getSenderId();
    String getReceiverId();
    String getMessageData();
    MessageType getMessageType();
    Date getDate();
    MessageStatus getMessageStatus();

    void setMessageData(String messageData);
}
