# Topic: Creational Design Patterns
## Course: Software Design Techniques and Mechanisms
## Author: Gutu Dinu

---

## Theory:
Creational design patterns are a category of design patterns that focus on the process 
of object creation. They provide a way to create objects in a flexible and controlled 
manner, while decoupling the client code from the specifics of object creation. 
Creational design patterns address common problems encountered in object creation, 
such as how to create objects with different initialization parameters, how to 
create objects based on certain conditions, or how to ensure that only a single 
instance of an object is created. There are several creational design patterns that 
have their own strengths and weaknesses. Each of it is best suited for solving 
specific problems related to object creation. By using creational design patterns, 
developers can improve the flexibility, maintainability, and scalability of their code.

## Objectives:

1. Study and understand the Creational Design Patterns.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Use some creational design patterns for object instantiation in a sample project.

## Implementation:

### Factory Method:
Factory Method is a creational design pattern that provides an interface for creating 
objects in a superclass, but allows subclasses to alter the type of objects that will 
be created.
```
public class AdminFactory implements UserFactory {
    @Override
    public User createUser(String userId, String username, String password) {
        return new AdminUser(userId, username, password);
    }
}

public class RegularUserFactory implements UserFactory {
    @Override
    public User createUser(String userId, String username, String password) {
        return new RegularUser(userId, username, password);
    }
}
```
### Singleton:
Singleton is a creational design pattern that lets you ensure that a class has 
only one instance, while providing a global access point to this instance.
```
public class UserManagerImpl implements UserManager{
    private static UserManagerImpl instance;
    private final Map<String, User> users;

    private UserManagerImpl() {
        users = new HashMap<>();
    }

    public static synchronized UserManagerImpl getInstance() {
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
```

### Object Pooling:
Object Pooling is a creational design pattern that pre-instantiates all the objects 
you’ll need at any specific moment before gameplay. This removes the need to create 
new objects or destroy old ones while the application is running.
```
public class UserPoolImpl implements UserPool{

    public UserPoolImpl(int maxSize, UserFactory regularFactory, UserFactory adminFactory) {
        this.maxSize = maxSize;
        this.userPool = new ArrayList<>(maxSize);
        ...

    public synchronized User acquireUser(String userId, String username, String password, boolean isAdmin) {
        if (userPool.isEmpty()) {
            return createUser(userId, username, password, isAdmin);
        } else {
            // Reuse an existing User object from the pool
            ...

    public synchronized void releaseUser(User user) {
        if (userPool.size() < maxSize) {
            userPool.add(user);
            ...

    private User createUser(String userId, String username, String password, boolean isAdmin) {
        // Call factory methods to create a new User object
        ...

```
### Builder
Builder is a creational design pattern that lets you construct complex objects step 
by step. The pattern allows you to produce different types and representations of an 
object using the same construction code.
```
public class MessageDataBuilder implements MessageBuilder{
    @Override
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public void setMessageData(String messageData) {
        this.messageData = messageData;
        ...
    
    @Override
    public Message build() {
        return new MessageData(messageId, senderId, ...);
    }

public class MessageDirector {

    public Message constructTextMessage(MessageBuilder messageBuilder, ...) {
        messageBuilder.setMessageId(String.valueOf(System.currentTimeMillis()));
        messageBuilder.setSenderId(senderId);
        ...
        return messageBuilder.build();
```
## Conclusion:
Creational design patterns are a fundamental aspect of software design. They provide 
structured solutions for object creation, addressing various challenges. 
These patterns offer flexibility in the creation of objects, allowing developers to 
change object types or instantiation processes without affecting the codebase. 
By encapsulating object creation logic, they promote code reusability, reducing 
redundancy and enhancing maintainability.
