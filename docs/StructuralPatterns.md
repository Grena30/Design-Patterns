# Topic: Structural Design Patterns
## Course: Software Design Techniques and Mechanisms
## Author: Gutu Dinu

---

## Theory:
Structural design patterns are a category of design patterns that focus on the
composition of classes and objects to form larger structures and systems. 
They provide a way to organize objects and classes in a way that is both 
flexible and efficient, while allowing for the reuse and modification of existing 
code. Structural design patterns address common problems encountered in the 
composition of classes and objects, such as how to create new objects that inherit 
functionality from existing objects, how to create objects that share functionality
without duplicating code, or how to define relationships between objects in a 
flexible and extensible way.

## Objectives:

1. Study and understand the Structural Design Patterns.
2. As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.
3. Implement some additional functionalities, or create a new project using structural design patterns.

## Implementation:

### Facade:
Facade is a structural design pattern that provides a simplified interface to a 
library, a framework, or any other complex set of classes.
```java
public class MessageCommunicationFacade {
    public MessageCommunicationFacade(MessageStorage messageStorage, ...) {
        ...
        this.userAuthenticationService = userAuthenticationService;
    }

    public void sendMessage(User sender, User receiver, Message message) {
        messageServiceDecorator.sendMessage(sender, receiver, message);
        System.out.println("Message sent.");

    public void receiveMessageUser(User user, String secretKey) {
        System.out.println("Messages for " + user.getUsername() + ":");
        List<Message> messageList = messageServiceDecorator.getMessages(user);
        User loggedInUser = userAuthenticationService.login(user.getUsername(), secretKey);
        ...
        
        printMessage(userManagementService, messageList);

    public void viewMessageAdmin(User user) {
        System.out.println("Messages for " + user.getUsername() + ":");
        List<Message> messageList = messageServiceDecorator.sendUserMessages(messageServiceDecorator.getMessages(user));
        printMessage(userManagementService, messageList);
    }

    private void printMessage(UserManagementService userService, List<Message> messages) {
        for (Message msg : messages) {
            User senderUser = userService.getUserById(msg.getSenderId());
            ...
```

### Decorator:
Decorator is a structural design pattern that lets you attach new behaviors to 
objects by placing these objects inside special wrapper objects that contain the 
behaviors.
```java
public class MessageEncryptionDecorator extends MessageServiceDecorator {

    public MessageEncryptionDecorator(MessageService messageService) {
        super(messageService);
    }

    @Override
    public void sendMessage(User sender, User receiver, Message message) {
        message.setMessageData(encrypt(message, 15));
        super.sendMessage(sender, receiver, message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return super.getMessages(user);
    }

    @Override
    public List<Message> sendUserMessages(List<Message> messages) {
            // Decrypt message
            ...
            userMessages.add(newMessage);
            ...
            return super.sendUserMessages(userMessages);

    private String encrypt(Message message, int shift) {
        ...

    private String decrypt(Message message, int shift) {
        ...
```

### Adapter:
Adapter is a structural design pattern that allows objects with incompatible 
interfaces to collaborate.
```java
public class UserAuthenticationAdapter implements UserAdditionService {

    private final UserAuthenticationService userAuthenticationService;
    public UserAuthenticationAdapter(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public void addUser(String username, String password, boolean isAdmin) {
        userAuthenticationService.registerUser(username, password, isAdmin);
    }
}
```

### Flyweight:
Flyweight is a structural design pattern that lets you fit more objects into the 
available amount of RAM by sharing common parts of state between multiple objects 
instead of keeping all of the data in each object.
```java
public class UserFactory {
    public static User getUser(String userId, String username, String password, UserType userType) {
        String key = username + userType.toString();

        if (userCache.containsKey(key)) {
            return userCache.get(key);
        }

        User user;
        if (userType == UserType.REGULAR_USER) {
            user = new RegularUser(userId, username, password);
            ...
            
        userCache.put(key, user);
        return user;
```

## Conclusion:
Structural design patterns are crucial elements of software architecture that focus 
on the composition and organization of classes and objects. These patterns provide 
solutions to common problems related to the structure of a software system.
They offer flexibility and promote the creation of reusable, maintainable code by 
addressing challenges in software design. Through encapsulation, they help separate 
concerns in the codebase, making it more manageable and adaptable as requirements 
evolve. These patterns ensure that the structure of the codebase is consistent and 
robust, reducing the risk of errors and inconsistencies, especially in larger and 
more complex software systems. By choosing the appropriate structural pattern for 
a given context, developers can improve the overall architecture and design of 
their software.