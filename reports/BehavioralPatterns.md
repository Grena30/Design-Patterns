# Topic: Behavioral Design Patterns
## Course: Software Design Techniques and Mechanisms
## Author: Gutu Dinu

---

## Theory:
Behavioral design patterns are a category of design patterns that focus on 
the interaction and communication between objects and classes. 
They provide a way to organize the behavior of objects in a way that is both 
flexible and reusable, while separating the responsibilities of objects from the 
specific implementation details. Behavioral design patterns address common problems 
encountered in object behavior, such as how to define interactions between objects, 
how to control the flow of messages between objects, or how to define algorithms and 
policies that can be reused across different objects and classes.

## Objectives:

1. Study and understand the Behavioral Design Patterns.
2. As a continuation of the previous laboratory work, think about what communication between software entities might be involed in your system.
3. Create a new Project or add some additional functionalities using behavioral design patterns.

## Implementation:

### Iterator:
Iterator is a behavioral design pattern that lets you traverse elements of a 
collection without exposing its underlying representation (list, stack, tree, etc.).

```
public class MessageIteratorImpl implements MessageIterator {
    public MessageIteratorImpl(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < messages.size();
    }
    
    @Override
    public Message getNext() {
        ...
        Message message = messages.get(currentIndex);
        currentIndex++;
```

### Strategy:
Strategy is a behavioral design pattern that lets you define a family of algorithms, 
put each of them into a separate class, and make their objects interchangeable.

```
public interface EncryptionStrategy {
    String encrypt(String message, String key);
    String decrypt(String message, String key);
}

public class CaesarCipher implements EncryptionStrategy {
    @Override
    public String encrypt(String message, String key) {
        StringBuilder encryptedData = new StringBuilder();
        int shift = getUsernameKey(key);
    ...

    @Override
    public String decrypt(String message, String key) {
        StringBuilder decryptedData = new StringBuilder();
        int shift = getUsernameKey(key);
        ...
```

### Observer:
Observer is a behavioral design pattern that lets you define a subscription mechanism 
to notify multiple objects about any events that happen to the object they’re observing.

```
public class EventManager {
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String username) {
        ...

public class UserLoginListener implements EventListener {
    public UserLoginListener(String user) {
        this.user = user;
    }
    @Override
    public void update(String evenType, String username) {
        System.out.println("To user: " + user + ". User " + username + " has performed a " + evenType);
    }

public class ChatRoom {
    public void login(String username) {
        events.notify("log-in", username);
    }
    
    public void logout(String username) {
        events.notify("log-out", username);
```

## Conclusion:
In conclusion, behavioral design patterns play a crucial role in software development by addressing 
the interactions and responsibilities between objects and classes in a system. 
These patterns provide effective solutions to various challenges related to the 
management of object behavior, communication, and control flow.

Behavioral design patterns promote code reusability, flexibility, and maintainability 
by encapsulating behavior in separate objects and promoting loose coupling between components. 
They enhance the overall design of a system by making it more modular and easier to understand, 
maintain, and extend.