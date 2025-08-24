import java.util.Scanner;
public class StringSplit {
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
public static String[] customSplit(String text) {
int len = findLength(text);
int wordCount = 1;
for (int i = 0; i < len; i++) {
if (text.charAt(i) == ' ') wordCount++;
}
int[] spaceIndexes = new int[wordCount - 1];
int idx = 0;
for (int i = 0; i < len; i++) {
if (text.charAt(i) == ' ') spaceIndexes[idx++] = i;
}
String[] words = new String[wordCount];
int start = 0;
for (int i = 0; i < wordCount - 1; i++) {
words[i] = text.substring(start, spaceIndexes[i]);
start = spaceIndexes[i] + 1;
}
words[wordCount - 1] = text.substring(start, len);
return words;
}
public static boolean compareArrays(String[] a, String[] b) {
if (a.length != b.length) return false;
for (int i = 0; i < a.length; i++) {
if (!a[i].equals(b[i])) return false;
}
return true;
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a sentence: ");
String text = sc.nextLine();
String[] custom = customSplit(text);
String[] builtIn = text.split(" ");
System.out.println("Custom split:");
for (String w : custom) System.out.println(w);
System.out.println("Built-in split:");
for (String w : builtIn) System.out.println(w);
System.out.println("Are they equal? " + compareArrays(custom, builtIn));
}
}