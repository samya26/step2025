import java.util.*;

public class replacesubstring {
    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + 1);
        }
        return positions;
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); ) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String manual, String text, String find, String replace) {
        return manual.equals(text.replace(find, replace));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String find = sc.nextLine();
        String replace = sc.nextLine();
        List<Integer> positions = findOccurrences(text, find);
        String manualResult = manualReplace(text, find, replace);
        boolean comparison = compareWithBuiltIn(manualResult, text, find, replace);
        System.out.println("Original Text: " + text);
        System.out.println("Manual Replace Result: " + manualResult);
        System.out.println("Built-in Replace Result: " + text.replace(find, replace));
        System.out.println("Comparison: " + comparison);
        System.out.println("Occurrences at positions: " + positions);
    }
}
