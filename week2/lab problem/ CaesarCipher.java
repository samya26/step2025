import java.util.*;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAscii(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c + "(" + (int) c + ") ");
        }
        System.out.println();
    }

    public static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int shift = sc.nextInt();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Original Text:");
        displayAscii(text);
        System.out.println("Encrypted Text:");
        displayAscii(encrypted);
        System.out.println("Decrypted Text:");
        displayAscii(decrypted);
        System.out.println("Validation: " + validate(text, decrypted));
    }
}

