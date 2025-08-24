import java.util.*;

public class textprocessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int sentenceCount = text.split("[.!?]").length;
        int charCount = text.replace(" ", "").length();

        String longestWord = "";
        for (String word : words) {
            String clean = word.replaceAll("[^a-zA-Z]", "");
            if (clean.length() > longestWord.length()) {
                longestWord = clean;
            }
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.replaceAll("\\s", "").toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }

        System.out.println("Word Count: " + wordCount);
        System.out.println("Sentence Count: " + sentenceCount);
        System.out.println("Character Count (no spaces): " + charCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: " + mostCommonChar);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.println("Enter a paragraph of text:");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);
        System.out.println("\nCleaned Input: " + cleaned);

        System.out.println("\n--- Text Analysis ---");
        analyzeText(cleaned);

        System.out.println("\n--- Words in Alphabetical Order ---");
        String[] sortedWords = getWordsSorted(cleaned);
        for (String w : sortedWords) {
            System.out.println(w);
        }

        System.out.print("\nEnter a word to search: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = Arrays.asList(sortedWords).contains(search);
        System.out.println("Word found: " + found);

        scanner.close();
    }
}
