package strategy;

public class CaesarCipher implements EncryptionStrategy {
    @Override
    public String encrypt(String message, String key) {
        StringBuilder encryptedData = new StringBuilder();
        int shift = getUsernameKey(key);

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) (currentChar + shift);

                if ((Character.isLowerCase(currentChar) && shiftedChar > 'z') ||
                        (Character.isUpperCase(currentChar) && shiftedChar > 'Z')) {
                    shiftedChar = (char) (currentChar - (26 - shift));
                }

                encryptedData.append(shiftedChar);
            } else {
                encryptedData.append(currentChar);
            }
        }

        return encryptedData.toString();
    }

    @Override
    public String decrypt(String message, String key) {
        StringBuilder decryptedData = new StringBuilder();
        int shift = getUsernameKey(key);

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) (currentChar - shift);

                if ((Character.isLowerCase(currentChar) && shiftedChar < 'a') ||
                        (Character.isUpperCase(currentChar) && shiftedChar < 'A')) {
                    shiftedChar = (char) (currentChar + (26 - shift));
                }

                decryptedData.append(shiftedChar);
            } else {
                decryptedData.append(currentChar);
            }
        }

        return decryptedData.toString();
    }

    private static int getUsernameKey(String username) {
        int key = 0;

        for (char ch : username.toCharArray()) {
            key += (int) ch;
        }

        key = key % 26;

        return key;
    }
}
