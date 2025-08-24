// PRACTICE PROBLEM 1
public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";
        System.out.println("Original Length: " + sampleText.length());
        String trimmed = sampleText.trim();
        System.out.println("Trimmed Length: " + trimmed.length());
        System.out.println("Character at index 5: " + sampleText.charAt(5));
        System.out.println("Substring: " + trimmed.substring(trimmed.indexOf("Programming"), trimmed.indexOf("Programming") + "Programming".length()));
        System.out.println("Index of 'Fun': " + trimmed.indexOf("Fun"));
        System.out.println("Contains 'Java': " + trimmed.contains("Java"));
        System.out.println("Starts with 'Java': " + trimmed.startsWith("Java"));
        System.out.println("Ends with '!': " + trimmed.endsWith("!"));
        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());
        System.out.println("Vowel Count: " + countVowels(trimmed));
        findAllOccurrences(trimmed, 'a');
    }
    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) count++;
        }
        return count;
    }
    public static void findAllOccurrences(String text, char target) {
        System.out.print("Occurrences of '" + target + "': ");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) System.out.print(i + " ");
        }
        System.out.println();
    }
}
