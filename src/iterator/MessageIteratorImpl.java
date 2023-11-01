package iterator;

import messaging.Message;

import java.util.List;

public class MessageIteratorImpl implements MessageIterator {
    private final List<Message> messages;
    private int currentIndex;

    public MessageIteratorImpl(List<Message> messages) {
        this.messages = messages;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < messages.size();
    }

    @Override
    public Message getNext() {
        if (!hasNext()) {
            return null;
        }

        Message message = messages.get(currentIndex);
        currentIndex++;
        return message;
    }
}