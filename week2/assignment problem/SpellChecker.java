import java.util.*;

public class SpellChecker {

    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetter(c)) {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static int stringDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    public static String findClosest(String word, String[] dictionary) {
        String best = word;
        int minDist = Integer.MAX_VALUE;
        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                best = dictWord;
            }
        }
        return minDist <= 2 ? best : word;
    }

    public static void displayResults(List<String> words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s\n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("---------------------------------------------------------------");
        for (String word : words) {
            String suggestion = findClosest(word, dictionary);
            int dist = stringDistance(word.toLowerCase(), suggestion.toLowerCase());
            String status = word.equalsIgnoreCase(suggestion) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-15s\n", word, suggestion, dist, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();
        System.out.println("Enter number of dictionary words:");
        int n = sc.nextInt();
        sc.nextLine();
        String[] dictionary = new String[n];
        System.out.println("Enter dictionary words:");
        for (int i = 0; i < n; i++) {
            dictionary[i] = sc.nextLine();
        }

        List<String> words = splitWords(sentence);
        displayResults(words, dictionary);
    }
}
