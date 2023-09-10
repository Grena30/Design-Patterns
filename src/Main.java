import auth.*;
import management.*;
import messaging.*;
import user.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserAuthenticationService authService = new UserAuthenticationServiceImpl();
        UserManagementService userService = new UserManagementServiceImpl();
        MessageStorage messageStorage = new MessageStorageImpl();
        MessageService messageService = new MessageServiceImpl(messageStorage);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChat Application Menu:");
            System.out.println("1. Register a new user");
            System.out.println("2. Log in");
            System.out.println("3. Send a message");
            System.out.println("4. View messages");
            System.out.println("5. Log out");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    User registeredUser = authService.registerUser(regUsername, regPassword);
                    userService.addUser(registeredUser);
                    System.out.println("User registered: " + registeredUser.getUsername());
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = authService.login(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("User logged in: " + loggedInUser.getUsername());
                    } else {
                        System.out.println("Login failed. Invalid credentials.");
                    }
                }
                case 3 -> {
                    if (authService.getLoggedInUsers().size() < 2) {
                        System.out.println("At least two users must be logged in to send a message.");
                        break;
                    }
                    System.out.println("Logged-in users:");
                    List<User> loggedInUsers = authService.getLoggedInUsers();
                    for (int i = 0; i < loggedInUsers.size(); i++) {
                        System.out.println((i + 1) + ". " + loggedInUsers.get(i).getUsername());
                    }
                    System.out.print("Select sender (1-" + loggedInUsers.size() + "): ");
                    int senderIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Select receiver (1-" + loggedInUsers.size() + "): ");
                    int receiverIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your message: ");
                    String messageText = scanner.nextLine();
                    User sender = loggedInUsers.get(senderIndex - 1);
                    User receiver = loggedInUsers.get(receiverIndex - 1);
                    Message message = new MessageText(
                            String.valueOf(System.currentTimeMillis()), sender.getUserId(), receiver.getUserId(), messageText,
                            MessageType.TEXT, new Date());
                    messageService.sendMessage(sender, receiver, message);
                    System.out.println("Message sent.");
                }
                case 4 -> {
                    if (authService.getLoggedInUsers().isEmpty()) {
                        System.out.println("No users are logged in.");
                        break;
                    }

                    System.out.println("Logged-in users:");
                    List<User> usersList = authService.getLoggedInUsers();
                    for (int i = 0; i < usersList.size(); i++) {
                        System.out.println((i + 1) + ". " + usersList.get(i).getUsername());
                    }

                    System.out.print("Select a user (1-" + usersList.size() + "): ");
                    int userIndex = scanner.nextInt();
                    scanner.nextLine();

                    User selectedUser = usersList.get(userIndex - 1);

                    System.out.println("Messages for " + selectedUser.getUsername() + ":");
                    List<Message> messages = messageService.getMessages(selectedUser);
                    for (Message msg : messages) {
                        User senderUser = userService.getUserById(msg.getSenderId());
                        System.out.println("From: " + senderUser.getUsername() + ", Message: " + msg.getMessageText() + ", Type: " + msg.getMessageType() + ", Date: " + msg.getDate());
                    }
                }
                case 5 -> {
                    List<User> loggedInUsers = authService.getLoggedInUsers();
                    if (loggedInUsers.isEmpty()) {
                        System.out.println("No users are logged in.");
                    } else {
                        System.out.println("Logged-in users:");
                        for (int i = 0; i < loggedInUsers.size(); i++) {
                            System.out.println((i + 1) + ". " + loggedInUsers.get(i).getUsername());
                        }

                        System.out.print("Select a user to log out (1-" + loggedInUsers.size() + "): ");
                        int userIndexToLogout = scanner.nextInt();
                        scanner.nextLine();

                        if (userIndexToLogout >= 1 && userIndexToLogout <= loggedInUsers.size()) {
                            User userToLogout = loggedInUsers.get(userIndexToLogout - 1);
                            authService.logout(userToLogout);
                            System.out.println(userToLogout.getUsername() + " logged out successfully.");
                        } else {
                            System.out.println("Invalid user selection.");
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Exiting Chat Application.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
