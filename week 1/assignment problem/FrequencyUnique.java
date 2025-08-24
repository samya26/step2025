import java.util.Scanner;
public class FrequencyUnique {
public static char[] getUniqueChars(String s) {
char[] temp = new char[s.length()];
int count = 0;
for (int i = 0; i < s.length(); i++) {
char c = s.charAt(i);
boolean unique = true;
for (int j = 0; j < i; j++) {
if (s.charAt(j) == c) {
unique = false;
break;
}
}
if (unique) temp[count++] = c;
}
char[] result = new char[count];
for (int i = 0; i < count; i++) result[i] = temp[i];
return result;
}
public static String[][] getFrequency(String s) {
int[] freq = new int[256];
for (int i = 0; i < s.length(); i++) freq[s.charAt(i)]++;
char[] unique = getUniqueChars(s);
String[][] result = new String[unique.length][2];
for (int i = 0; i < unique.length; i++) {
result[i][0] = String.valueOf(unique[i]);
result[i][1] = String.valueOf(freq[unique[i]]);
}
return result;
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String text = sc.nextLine();
String[][] freq = getFrequency(text);
for (String[] row : freq) System.out.println(row[0] + " : " + row[1]);
}
}