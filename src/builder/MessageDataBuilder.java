package builder;

import messaging.*;

import java.util.Date;

public class MessageDataBuilder implements MessageBuilder{
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageData;
    private MessageType messageType;
    private Date date;
    private MessageStatus messageStatus;

    @Override
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    @Override
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public Message build() {
        return new MessageData(messageId, senderId, receiverId, messageData, messageType, date, messageStatus);
    }
}
