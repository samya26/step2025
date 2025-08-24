import java.util.Scanner;
public class VowelConsonantTable {
public static String checkCharType(char ch) {
if (ch >= 'A' && ch <= 'Z') ch = (char)(ch + 32);
if (ch >= 'a' && ch <= 'z') {
if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return "Vowel";
else return "Consonant";
}
return "Not a Letter";
}
public static String[][] classifyChars(String s) {
String[][] result = new String[s.length()][2];
for (int i = 0; i < s.length(); i++) {
result[i][0] = String.valueOf(s.charAt(i));
result[i][1] = checkCharType(s.charAt(i));
}
return result;
}
public static void displayTable(String[][] arr) {
System.out.printf("%-10s %-15s\n", "Character", "Type");
for (String[] row : arr) {
System.out.printf("%-10s %-15s\n", row[0], row[1]);
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String str = sc.nextLine();
String[][] table = classifyChars(str);
displayTable(table);
}
}