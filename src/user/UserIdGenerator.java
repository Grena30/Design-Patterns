package user;

public class UserIdGenerator {
    private static int userIdCounter = 0;

    public static String generateUniqueUserId() {
        String userId = "user_" + userIdCounter;
        userIdCounter++;
        return userId;
    }
}
