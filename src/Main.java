import auth.*;
import management.*;
import messaging.*;
import user.*;
import builder.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static int selectionPrompt(List<User> loggedInUsers, String userType){
        System.out.print("Select a " + userType + " (1-" + loggedInUsers.size() + "): ");
        int userIndex = scanner.nextInt();
        scanner.nextLine();
        return userIndex;
    }

    private static List<String> inputPrompt() {
        System.out.print("Enter username: ");
        String regUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String regPassword = scanner.nextLine();

        return List.of(regUsername, regPassword);
    }

    private static List<User> getLoggedInUsers(UserAuthenticationService authService, boolean isAdmin) {
        if (authService.getLoggedInUsers(isAdmin).isEmpty()) {
            System.out.println("No users are logged in.");
            return null;
        }

        System.out.println("Logged-in users:");
        List<User> loggedInUsers = authService.getLoggedInUsers(isAdmin);
        for (int i = 0; i < loggedInUsers.size(); i++) {
            System.out.println((i + 1) + ". " + loggedInUsers.get(i).getUsername());
        }
        return loggedInUsers;
    }

    private static User validSelection(UserAuthenticationService authService, String userType, boolean isAdmin) {
        List<User> loggedInUsers = getLoggedInUsers(authService, isAdmin);
        if (loggedInUsers == null) {
            return null;
        }

        int userIndex = selectionPrompt(loggedInUsers, userType);

        if (!isValidInput(userIndex, loggedInUsers.size())) {
            System.out.println("Invalid selection.");
            return null;
        }
        return loggedInUsers.get(userIndex - 1);
    }
    private static int RegularUserMenu() {
        System.out.println("\nChat Application Menu:");
        System.out.println("1. Register a new user");
        System.out.println("2. Log in");
        System.out.println("3. Display user profile info");
        System.out.println("4. Send a message");
        return CommonMenu();
    }

    private static void RegularUserFunctionality(UserAuthenticationService authService,
                                                 UserManagementService userService,
                                                 MessageService messageService,
                                                 MessageBuilder messageTextBuilder) {
        while (true) {
            int choice = RegularUserMenu();
            boolean isAdmin = false;

            switch (choice) {
                case 1 -> {
                    List<String> input = inputPrompt();
                    User registeredUser = authService.registerUser(input.get(0), input.get(1), isAdmin);
                    System.out.println("User registered: " + registeredUser.getUsername());
                }
                case 2 -> login(authService);
                case 3 -> {
                    User selectedUser = validSelection(authService, "user", isAdmin);

                    if (selectedUser == null) {
                        break;
                    }

                    selectedUser.displayUserInfo();
                }
                case 4 -> {
                    User sender = validSelection(authService, "sender", isAdmin);

                    if (sender == null) {
                        break;
                    }

                    User receiver = validSelection(authService, "receiver", isAdmin);

                    if (receiver == null) {
                        break;
                    }

                    System.out.print("Enter your message: ");
                    String messageText = scanner.nextLine();
                    Message message = messageTextBuilder.messageId(String.valueOf(System.currentTimeMillis()))
                            .senderId(sender.getUserId())
                            .receiverId(receiver.getUserId())
                            .messageData(messageText)
                            .messageType(MessageType.TEXT)
                            .date(new Date())
                            .messageStatus(MessageStatus.SENT)
                            .build();
                    messageService.sendMessage(sender, receiver, message);
                    System.out.println("Message sent.");
                }
                case 5 -> {
                    User selectedUser = validSelection(authService, "user", isAdmin);

                    if (selectedUser == null) {
                        break;
                    }

                    viewMessages(userService, messageService, selectedUser);
                }
                case 6 -> {
                    User selectedUser = validSelection(authService, "user", isAdmin);

                    if (selectedUser == null) {
                        break;
                    }

                    authService.logout(selectedUser);
                    System.out.println(selectedUser.getUsername() + " logged out successfully.");
                }
                case 7 -> {
                    return;
                }
                case 8 -> {
                    System.out.println("Exiting Application.");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void login(UserAuthenticationService authService) {
            List<String> input = inputPrompt();
            User loggedInUser = authService.login(input.get(0), input.get(1));
            if (loggedInUser != null) {
                System.out.println("User logged in: " + loggedInUser.getUsername());
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }
        }

    private static int AdminUserMenu() {
        System.out.println("\nManagement Menu:");
        System.out.println("1. Register a new admin user");
        System.out.println("2. Log in");
        System.out.println("3. Display user info");
        System.out.println("4. Remove user");
        return CommonMenu();
    }

    private static void AdminUserFunctionality(UserAuthenticationService authService,
                                               UserManagementService userService,
                                               MessageService messageService) {
        while (true) {
            int choice = AdminUserMenu();
            boolean isAdmin = true;

            switch (choice) {
                case 1 -> {
                    List<String> input = inputPrompt();
                    User registeredUser = authService.registerUser(input.get(0), input.get(1), isAdmin);
                    System.out.println("User registered: " + registeredUser.getUsername());
                }
                case 2 -> login(authService);
                case 3 -> {
                    User selectedUser = validSelection(authService, "user", isAdmin);

                    if (selectedUser == null) {
                        break;
                    }

                    selectedUser.displayUserInfo();
                }
                case 4 ->{
                    User selectedUser = validSelection(authService, "user", false);

                    if (selectedUser == null) {
                        break;
                    }

                    userService.deleteUser(selectedUser.getUserId());
                    System.out.println("User deleted: " + selectedUser.getUsername());
                }
                case 5 ->{
                    User selectedUser = validSelection(authService, "user", false);

                    if (selectedUser == null) {
                        break;
                    }

                    viewMessages(userService, messageService, selectedUser);
                }
                case 6 -> {
                    User selectedUser = validSelection(authService, "user", isAdmin);

                    if (selectedUser == null) {
                        break;
                    }

                    authService.logout(selectedUser);
                    System.out.println(selectedUser.getUsername() + " logged out successfully.");
                }
                case 7 -> {
                    return;
                }
                case 8 -> {
                    System.out.println("Exiting Application.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }

    private static void viewMessages(UserManagementService userService, MessageService messageService, User selectedUser) {
        System.out.println("Messages for " + selectedUser.getUsername() + ":");
        List<Message> messages = messageService.getMessages(selectedUser);
        for (Message msg : messages) {
            User senderUser = userService.getUserById(msg.getSenderId());
            System.out.println("From: " + senderUser.getUsername() + ", Message: " + msg.getMessageData());
        }
    }

    private static int CommonMenu() {
        System.out.println("5. View messages");
        System.out.println("6. Log out");
        System.out.println("7. Return to main menu");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static boolean isValidInput(int choice, int max) {
        return choice >= 1 && choice <= max;
    }

    public static void main(String[] args) {
        UserAuthenticationService authService = new UserAuthenticationServiceImpl();
        UserManagementService userService = new UserManagementServiceImpl();
        MessageStorage messageStorage = new MessageStorageImpl();
        MessageService messageService = new MessageServiceImpl(messageStorage);
        MessageBuilder messageTextBuilder = new MessageTextBuilder();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1. Admin user");
            System.out.println("2. Regular user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> AdminUserFunctionality(authService, userService, messageService);
                case 2 -> RegularUserFunctionality(authService, userService, messageService, messageTextBuilder);
                case 3 -> {
                    System.out.println("Exiting Application.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
