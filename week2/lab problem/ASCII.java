import java.util.*;

public class ASCII {
    public static char toUpper(char c) {
        if (c >= 'a' && c <= 'z') return (char)(c - 32);
        return c;
    }

    public static char toLower(char c) {
        if (c >= 'A' && c <= 'Z') return (char)(c + 32);
        return c;
    }

    public static String convertToUpper(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toUpper(text.charAt(i)));
        return sb.toString();
    }

    public static String convertToLower(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toLower(text.charAt(i)));
        return sb.toString();
    }

    public static String convertToTitle(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                sb.append(c);
                newWord = true;
            } else {
                if (newWord) {
                    sb.append(toUpper(c));
                    newWord = false;
                } else {
                    sb.append(toLower(c));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String upper = convertToUpper(text);
        String lower = convertToLower(text);
        String title = convertToTitle(text);
        System.out.printf("%-20s %-20s %-20s\n", "Method", "Manual Result", "Built-in Result");
        System.out.printf("%-20s %-20s %-20s\n", "Uppercase", upper, text.toUpperCase());
        System.out.printf("%-20s %-20s %-20s\n", "Lowercase", lower, text.toLowerCase());
        System.out.printf("%-20s %-20s %-20s\n", "Title Case", title, "");
    }
}
