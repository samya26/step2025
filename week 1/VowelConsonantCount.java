import java.util.Scanner;
public class VowelConsonantCount {
public static String checkCharType(char ch) {
if (ch >= 'A' && ch <= 'Z') ch = (char)(ch + 32);
if (ch >= 'a' && ch <= 'z') {
if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return "Vowel";
else return "Consonant";
}
return "Not a Letter";
}
public static int[] findCounts(String s) {
int vowels = 0, consonants = 0;
for (int i = 0; i < s.length(); i++) {
String type = checkCharType(s.charAt(i));
if (type.equals("Vowel")) vowels++;
else if (type.equals("Consonant")) consonants++;
}
return new int[]{vowels, consonants};
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String str = sc.nextLine();
int[] counts = findCounts(str);
System.out.println("Vowels: " + counts[0]);
System.out.println("Consonants: " + counts[1]);
}
}