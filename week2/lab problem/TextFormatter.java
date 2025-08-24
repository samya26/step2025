import java.util.*;

public class TextFormatter {
    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            StringBuilder line = new StringBuilder();
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) line.append(" ");
                }
                while (line.length() < width) line.append(" ");
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line.append(" ");
                        if (extra-- > 0) line.append(" ");
                    }
                }
            }
            lines.add(line.toString());
            i = j;
        }
        return lines;
    }

    public static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            int padding = width - line.trim().length();
            int left = padding / 2;
            int right = padding - left;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < left; i++) sb.append(" ");
            sb.append(line.trim());
            for (int i = 0; i < right; i++) sb.append(" ");
            centered.add(sb.toString());
        }
        return centered;
    }

    public static long justifyWithConcat(List<String> words, int width) {
        long start = System.nanoTime();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            String line = "";
            for (int k = i; k < j; k++) {
                line += words.get(k);
                if (k < j - 1) line += " ";
            }
            i = j;
        }
        long end = System.nanoTime();
        return end - start;
    }

    public static void display(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("Line %d (%d chars): %s\n", i + 1, lines.get(i).length(), lines.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.println("Enter line width:");
        int width = sc.nextInt();
        List<String> words = splitWords(text);
        long sbStart = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long sbEnd = System.nanoTime();
        long sbTime = sbEnd - sbStart;
        List<String> centered = centerAlign(justified, width);
        long concatTime = justifyWithConcat(words, width);

        System.out.println("\nOriginal Text:\n" + text);
        System.out.println("\nJustified Text:");
        display(justified);
        System.out.println("\nCenter Aligned Text:");
        display(centered);
        System.out.println("\nPerformance Comparison:");
        System.out.println("StringBuilder Time: " + sbTime + " ns");
        System.out.println("String Concatenation Time: " + concatTime + " ns");
    }
}

