# Topic: SOLID Principles
## Course:
## Author: Gutu Dinu

---

## Theory:
SOLID is a set of five object-oriented design principles intended to make software 
designs more maintainable, flexible, and easy to understand. The acronym stands for 
Single Responsibility Principle, Open-Closed Principle, Liskov Substitution 
Principle, Interface Segregation Principle, and Dependency Inversion Principle. 
Each principle addresses a specific aspect of software design, such as the 
organization of responsibilities, the handling of dependencies, and the design of 
interfaces. By following these principles, software developers can create more 
modular, testable, and reusable code that is easier to modify and extend over time. 
These principles are widely accepted and adopted in the software development 
community and can be applied to any object-oriented programming language.

## Objectives:

1. Study and understand the SOLID Principles.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Create a sample project that respects SOLID Principles.

## Tasks:

1. Choose an OO programming language and a suitable IDE or Editor (No frameworks/libs/engines allowed).
2. Select a domain area for the sample project.
3. Define the main involved classes and think about what instantiation mechanisms are needed.
4. Respect SOLID Principles in your project.

## Implementation:

### Authentication

The interface which defines the methods for user authentication.

```
public interface UserAuthenticationService {
    RegularUser registerUser(String username, String password);
    RegularUser login(String username, String password);
    void logout(RegularUser user);
    List<RegularUser> getLoggedInUsers();
}
```

A sample method from the implementation of the interface.

```
public class UserAuthenticationServiceImpl implements UserAuthenticationService{
    private UserManager userManager = UserManager.getInstance();
    private Set<String> loggedInUsers = new HashSet<>();
    private int userIdCounter = 0;

    @Override
    public RegularUser registerUser(String username, String password) {
        if (userManager.getUsers().containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        String userId = generateUniqueUserId();
        RegularUser user = new RegularUser(userId, username, password);
        userManager.addUser(user);
        return user;
        ...
    }
```

### User Management

The interface which defines the methods for user management.

```
public interface UserManagementService {
    RegularUser getUserById(String userId);
    void updateUserProfile(RegularUser user, UserProfile profileData);
    List<RegularUser> getUserList();

}
```

A sample method from the implementation of the interface.

```
public class UserManagementServiceImpl implements UserManagementService{

        private UserManager userManager = UserManager.getInstance();

        @Override
        public RegularUser getUserById(String userId) {
                for (RegularUser user : userManager.getUsers().values()) {
                        if (user.getUserId().equals(userId)) {
                                return user;
                        }
                }
                return null;
        }
        ...
```

### Messaging

The messaging is about storing and retrieving/sending messages.
Here is a sample of the interface which defines the methods
for a simple text message.

```
public interface Message {
    String getMessageId();
    String getSenderId();
    String getReceiverId();
    String getMessageText();
    MessageType getMessageType();
    Date getDate();
}
```

A sample method from the implementation of the interface.

```
public class MessageText implements Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageText;
    private MessageType messageType;
    private Date date;   
    ...
    
    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }
    ...
```

### User

A simple abstract class which defines the parameters and methods of the user.
It is inherited by Regular User and Admin User.

```
abstract class User {
    private String userId;
    private String username;
    private String password;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
    
    public void displayUserInfo() {}   
    ...
```

Sample implementation of a method from Regular User.

```
public class RegularUser extends User{

    public RegularUser(String userId, String username, String password) {
        super(userId, username, password);
        this.profile = new UserProfile();
    }

    @Override
    public void displayUserInfo() {
        System.out.println("User ID: " + getUserId());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
    }
    ...
```


## Following SOLID principles:

### Single Responsibility Principle:
1. The UserAuthenticationServiceImpl class has a single responsibility, which 
is to handle user authentication, including registration, login, and logout.
2. The UserManagementServiceImpl class has a single responsibility for user 
management, such as retrieving user profiles and maintaining the user list.
3. The MessageServiceImpl class focuses on sending and retrieving messages, 
adhering to the principle by managing message-related tasks.

### Open/Closed Principle:
1. The application's classes are designed to be open 
for extension but closed for modification. 
New features or functionality can be added through new 
classes or interfaces without modifying existing code. 
For example, you can introduce new message types or user 
authentication methods without altering the existing classes.

### Liskov Substitution Principle:
1. The application's classes, including both interfaces and their implementations, 
as well as abstract classes, adhere to the principle. Subtypes (implementations) 
of these interfaces and abstract classes can seamlessly replace their base 
types (interfaces or abstract classes) without introducing issues that would impact 
the correctness of the program. This adherence to principles ensures that different 
classes, whether based on interfaces or abstract classes, can be used 
interchangeably while maintaining program integrity.

### Interface Segregation Principle:
1. The interfaces defined in the 
application are relatively small and 
focused on specific functionalities. 
For example, UserAuthenticationService and UserManagementService 
have clear, focused methods related to their respective responsibilities.

### Dependency Inversion Principle:
1. The application follows Dependency Inversion Principle by relying on 
abstractions (interfaces) rather than concrete implementations. For instance, 
MessageServiceImpl depends on the MessageService interface, not a specific 
implementation of MessageService. This allows for the flexibility to use 
different implementations of the message service without modifying the 
dependent classes.

## Results:
### An example of one user sending a message to another
```
Chat Application Menu:
1. Register a new user
2. Log in
3. Display user info
4. Send a message
5. View messages
6. Log out
7. Exit
Enter your choice: 1
Enter username: hello
Enter password: hello
User registered: hello

Chat Application Menu:
...
Enter your choice: 1
Enter username: world
Enter password: world
User registered: world

Chat Application Menu:
...
Enter your choice: 2
Enter username: world
Enter password: world
User logged in: world

Chat Application Menu:
...
Enter your choice: 2
Enter username: hello
Enter password: hello
User logged in: hello

Chat Application Menu:
...
Enter your choice: 4
Logged-in users:
1. world
2. hello
Select a sender (1-2): 1
Logged-in users:
1. world
2. hello
Select a receiver (1-2): 2
Enter your message: How are you?
Message sent.

Chat Application Menu:
...
Enter your choice: 5
Logged-in users:
1. world
2. hello
Select a user (1-2): 2
Messages for hello:
From: world, Message: How are you?, Type: TEXT, Date: Wed Sep 13 18:01:51 EEST 2023

```


## Conclusion:
In conclusion, using the SOLID principles when developing a 
chat application gave me a well-organized and efficient approach. 
These principles have helped improve my code's quality and maintainability 
while also laying the groundwork for future enhancements 
and adaptations. Adopting SOLID principles has surely been a rewarding 
attempt in my effort to develop an effective chat program, even 
though it necessitates careful planning and up-front work.