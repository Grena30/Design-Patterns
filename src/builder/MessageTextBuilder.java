package builder;

import messaging.Message;
import messaging.MessageStatus;
import messaging.MessageText;
import messaging.MessageType;

import java.util.Date;

public class MessageTextBuilder implements MessageBuilder{
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageData;
    private MessageType messageType;
    private Date date;
    private MessageStatus messageStatus;

    @Override
    public MessageBuilder messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    @Override
    public MessageBuilder senderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    @Override
    public MessageBuilder receiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    @Override
    public MessageBuilder messageData(String messageData) {
        this.messageData = messageData;
        return this;
    }

    @Override
    public MessageBuilder messageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    @Override
    public MessageBuilder date(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public MessageBuilder messageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    @Override
    public Message build() {
        if (messageId == null || senderId == null || receiverId == null || messageData == null) {
            throw new IllegalStateException("The mandatory fields are not set.");
        }
        return new MessageText(messageId, senderId, receiverId, messageData, messageType, date, messageStatus);
    }
}
