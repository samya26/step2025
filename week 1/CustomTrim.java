import java.util.Scanner;
public class CustomTrim {
public static int[] findTrimIndexes(String s) {
int start = 0, end = s.length() - 1;
while (start < s.length() && s.charAt(start) == ' ') start++;
while (end >= 0 && s.charAt(end) == ' ') end--;
return new int[]{start, end + 1};
}
public static String customSubstring(String s, int start, int end) {
StringBuilder sb = new StringBuilder();
for (int i = start; i < end; i++) sb.append(s.charAt(i));
return sb.toString();
}
public static boolean compareStrings(String s1, String s2) {
if (s1.length() != s2.length()) return false;
for (int i = 0; i < s1.length(); i++) {
if (s1.charAt(i) != s2.charAt(i)) return false;
}
return true;
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String str = sc.nextLine();
int[] indexes = findTrimIndexes(str);
String trimmedCustom = customSubstring(str, indexes[0], indexes[1]);
String trimmedBuiltIn = str.trim();
System.out.println("Custom Trim: [" + trimmedCustom + "]");
System.out.println("Built-in Trim: [" + trimmedBuiltIn + "]");
System.out.println("Are equal? " + compareStrings(trimmedCustom,
trimmedBuiltIn));
}
}