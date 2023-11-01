package iterator;

import messaging.Message;

public interface MessageIterator {
    boolean hasNext();
    Message getNext();
}
