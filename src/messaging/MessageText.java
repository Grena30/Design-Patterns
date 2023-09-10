package messaging;

import java.util.Date;

public class MessageText implements Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageText;
    private MessageType messageType;
    private Date date;

    public MessageText(String messageId, String senderId, String receiverId, String messageText, MessageType messageType, Date date) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.messageType = messageType;
        this.date = date;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessageText() {
        return messageText;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Date getDate() {
        return date;
    }
}
