package Algoritm_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) { //scanner
        Scanner scanner = new Scanner(System.in);

        String inputText = scanner.next();

        int shift = 3;

        // Зашифровать текст
        String encryptedText = encrypt(inputText, shift);
        System.out.println("Зашифрованный текст: " + encryptedText);

        // Расшифровать текст
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Расшифрованный текст: " + decryptedText);
    }
    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                encryptedText.append((char) ((character - base + shift) % 26 + base));
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift); // Расшифровка - это шифрование с обратным сдвигом
    }
}
