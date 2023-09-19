package messaging;

import java.util.Date;

public class MessageData implements Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageText;
    private MessageType messageType;
    private Date date;
    private MessageStatus messageStatus;

    public MessageData(String messageId, String senderId, String receiverId, String messageText,
                       MessageType messageType, Date date, MessageStatus messageStatus) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.messageType = messageType;
        this.date = date;
        this.messageStatus = messageStatus;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getSenderId() {
        return senderId;
    }

    @Override
    public String getReceiverId() {
        return receiverId;
    }

    @Override
    public String getMessageData() {
        return messageText;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public MessageStatus getMessageStatus() {
        return messageStatus;
    }
}
