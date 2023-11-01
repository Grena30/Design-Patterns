package strategy;

public interface EncryptionStrategy {
    String encrypt(String message, String key);
    String decrypt(String message, String key);
}
