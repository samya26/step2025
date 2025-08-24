import java.util.Scanner;
public class UniqueCharacters {
public static int findLength(String s) {
int count = 0;
try {
while (true) {
s.charAt(count);
count++;
}
} catch (IndexOutOfBoundsException e) {
return count;
}
}
public static char[] getUniqueChars(String s) {
int len = findLength(s);
char[] temp = new char[len];
int count = 0;
for (int i = 0; i < len; i++) {
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
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String text = sc.nextLine();
char[] unique = getUniqueChars(text);
for (char c : unique) System.out.print(c + " ");
}
}