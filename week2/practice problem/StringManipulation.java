// PRACTICE PROBLEM 2
import java.util.*;
public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String trimmed = input.trim();
        String replaced = trimmed.replace(" ", "_");
        String noDigits = replaced.replaceAll("\\d", "");
        String[] words = noDigits.split("_");
        String joined = String.join(" | ", words);
        System.out.println("Joined: " + joined);
        System.out.println(removePunctuation(trimmed));
        System.out.println(capitalizeWords(trimmed));
        System.out.println(reverseWordOrder(trimmed));
        countWordFrequency(trimmed);
        scanner.close();
    }
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }
    public static String capitalizeWords(String text) {
        String[] parts = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p.length() > 0) sb.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }
    public static String reverseWordOrder(String text) {
        String[] parts = text.split(" ");
        Collections.reverse(Arrays.asList(parts));
        return String.join(" ", parts);
    }
    public static void countWordFrequency(String text) {
        String[] parts = text.toLowerCase().split(" ");
        Map<String,Integer> map = new HashMap<>();
        for (String p : parts) map.put(p,map.getOrDefault(p,0)+1);
        System.out.println(map);
    }
}
