package strategy;

public class VigenereCipher implements EncryptionStrategy{
    public String encrypt(String message, String key) {
        message = message.toUpperCase();
        key = key.toUpperCase();
        StringBuilder encryptionBuilder = new StringBuilder();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                int shift = key.charAt(j) - 'A';
                char shiftedChar = (char) (((currentChar + shift - 'A') % 26) + 'A');
                encryptionBuilder.append(shiftedChar);
                j = (j + 1) % key.length();
            } else {
                encryptionBuilder.append(currentChar);
            }
        }

        return encryptionBuilder.toString();
    }

    public String decrypt(String message, String key) {
        message = message.toUpperCase();
        key = key.toUpperCase();
        StringBuilder decryptionBuilder = new StringBuilder();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                int shift = key.charAt(j) - 'A';
                char originalChar = (char) (((currentChar - shift - 'A' + 26) % 26) + 'A');
                decryptionBuilder.append(originalChar);
                j = (j + 1) % key.length();
            } else {
                decryptionBuilder.append(currentChar);
            }
        }

        return decryptionBuilder.toString();
    }

}
