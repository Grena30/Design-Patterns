package messaging;

import java.util.Date;

public interface Message {
    String getMessageId();
    String getSenderId();
    String getReceiverId();
    String getMessageText();
    MessageType getMessageType();
    Date getDate();
}
