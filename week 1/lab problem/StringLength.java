import java.util.Scanner;
public class StringLength {
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
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a string: ");
String input = sc.next();
int customLength = findLength(input);
int builtInLength = input.length();
System.out.println("Custom length: " + customLength);
System.out.println("Built-in length: " + builtInLength);
}
}