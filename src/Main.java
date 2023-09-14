import auth.*;
import management.*;
import messaging.*;
import user.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static int selectionPrompt(List<RegularUser> loggedInUsers, String userType){
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

    private static List<RegularUser> getLoggedInUsers(UserAuthenticationService authService) {
        if (authService.getLoggedInUsers().isEmpty()) {
            System.out.println("No users are logged in.");
            return null;
        }

        System.out.println("Logged-in users:");
        List<RegularUser> loggedInUsers = authService.getLoggedInUsers();
        for (int i = 0; i < loggedInUsers.size(); i++) {
            System.out.println((i + 1) + ". " + loggedInUsers.get(i).getUsername());
        }
        return loggedInUsers;
    }

    private static RegularUser validSelection(UserAuthenticationService authService, String userType) {
        List<RegularUser> loggedInUsers = getLoggedInUsers(authService);
        if (loggedInUsers == null) {
            return null;
        }

        int userIndex = selectionPrompt(loggedInUsers, userType);

        if (!isValidInput(userIndex, 1, loggedInUsers.size())) {
            System.out.println("Invalid selection.");
            return null;
        }
        return loggedInUsers.get(userIndex - 1);
    }

    private static boolean isValidInput(int choice, int min, int max) {
        return choice >= min && choice <= max;
    }

    private static int menu() {
        System.out.println("\nChat Application Menu:");
        System.out.println("1. Register a new user");
        System.out.println("2. Log in");
        System.out.println("3. Display user info");
        System.out.println("4. Send a message");
        System.out.println("5. View messages");
        System.out.println("6. Log out");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static void main(String[] args) {
        UserAuthenticationService authService = new UserAuthenticationServiceImpl();
        UserManagementService userService = new UserManagementServiceImpl();
        MessageStorage messageStorage = new MessageStorageImpl();
        MessageService messageService = new MessageServiceImpl(messageStorage);

        while (true) {
            int choice = menu();

            switch (choice) {
                case 1 -> {
                    List<String> input = inputPrompt();
                    RegularUser registeredUser = authService.registerUser(input.get(0), input.get(1));
                    System.out.println("User registered: " + registeredUser.getUsername());
                }
                case 2 -> {
                    List<String> input = inputPrompt();
                    RegularUser loggedInUser = authService.login(input.get(0), input.get(1));
                    if (loggedInUser != null) {
                        System.out.println("User logged in: " + loggedInUser.getUsername());
                    } else {
                        System.out.println("Login failed. Invalid credentials.");
                    }
                }
                case 3 -> {
                    RegularUser selectedUser = validSelection(authService, "user");

                    if (selectedUser == null) {
                        break;
                    }

                    selectedUser.displayUserInfo();
                }
                case 4 -> {
                    RegularUser sender = validSelection(authService, "sender");

                    if (sender == null) {
                        break;
                    }

                    RegularUser receiver = validSelection(authService, "receiver");

                    if (receiver == null) {
                        break;
                    }

                    System.out.print("Enter your message: ");
                    String messageText = scanner.nextLine();
                    Message message = new MessageText(
                            String.valueOf(System.currentTimeMillis()), sender.getUserId(), receiver.getUserId(), messageText,
                            MessageType.TEXT, new Date());
                    messageService.sendMessage(sender, receiver, message);
                    System.out.println("Message sent.");
                }
                case 5 -> {
                    RegularUser selectedUser = validSelection(authService, "user");

                    if (selectedUser == null) {
                        break;
                    }

                    System.out.println("Messages for " + selectedUser.getUsername() + ":");
                    List<Message> messages = messageService.getMessages(selectedUser);
                    for (Message msg : messages) {
                        RegularUser senderUser = userService.getUserById(msg.getSenderId());
                        System.out.println("From: " + senderUser.getUsername() + ", Message: " + msg.getMessageText() + ", Type: " + msg.getMessageType() + ", Date: " + msg.getDate());
                    }
                }
                case 6 -> {
                    RegularUser selectedUser = validSelection(authService, "user");

                    if (selectedUser == null) {
                        break;
                    }

                    authService.logout(selectedUser);
                    System.out.println(selectedUser.getUsername() + " logged out successfully.");
                }
                case 7 -> {
                    System.out.println("Exiting Chat Application.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}