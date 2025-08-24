import java.util.*;

public class TextCompressor {

    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int uniqueCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (chars[j] == c) {
                    freq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                chars[uniqueCount] = c;
                freq[uniqueCount] = 1;
                uniqueCount++;
            }
        }
        return new Object[]{Arrays.copyOf(chars, uniqueCount), Arrays.copyOf(freq, uniqueCount)};
    }

    public static String[][] createCodes(char[] chars, int[] freq) {
        Integer[] indices = new Integer[chars.length];
        for (int i = 0; i < chars.length; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> freq[b] - freq[a]);
        String[][] codes = new String[chars.length][2];
        for (int i = 0; i < indices.length; i++) {
            codes[i][0] = String.valueOf(chars[indices[i]]);
            codes[i][1] = Integer.toString(i, 36);
        }
        return codes;
    }

    public static String compress(String text, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            for (String[] code : codes) {
                if (code[0].equals(c)) {
                    sb.append(code[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static String decompress(String compressed, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            String s = String.valueOf(compressed.charAt(i));
            boolean matched = false;
            for (String[] code : codes) {
                if (code[1].equals(s)) {
                    sb.append(code[0]);
                    matched = true;
                    break;
                }
            }
            if (!matched) sb.append("?");
            i++;
        }
        return sb.toString();
    }

    public static void displayAnalysis(String text, String compressed, String decompressed, char[] chars, int[] freq, String[][] codes) {
        System.out.println("Character Frequency Table:");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }
        System.out.println("\nCompression Mapping:");
        for (String[] code : codes) {
            System.out.println(code[0] + " -> " + code[1]);
        }
        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);
        double ratio = ((double) compressed.length() / text.length()) * 100;
        System.out.printf("Compression Efficiency: %.2f%%\n", 100 - ratio);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = sc.nextLine();
        Object[] result = countFrequency(text);
        char[] chars = (char[]) result[0];
        int[] freq = (int[]) result[1];
        String[][] codes = createCodes(chars, freq);
        String compressed = compress(text, codes);
        String decompressed = decompress(compressed, codes);
        displayAnalysis(text, compressed, decompressed, chars, freq, codes);
    }
}
