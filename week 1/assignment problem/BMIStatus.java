import java.util.Scanner;
public class BMIStatus {
public static String[] computeBMI(double weight, double heightCm) {
double heightM = heightCm / 100;
double bmi = weight / (heightM * heightM);
String status;
if (bmi < 18.5) status = "Underweight";
else if (bmi < 25) status = "Normal";
else if (bmi < 30) status = "Overweight";
else status = "Obese";
return new String[]{String.format("%.2f", bmi), status};
}
public static String[][] processData(double[][] hw) {
String[][] result = new String[hw.length][4];
for (int i = 0; i < hw.length; i++) {
String[] bmiData = computeBMI(hw[i][0], hw[i][1]);
result[i][0] = String.format("%.2f", hw[i][1]);
result[i][1] = String.format("%.2f", hw[i][0]);
result[i][2] = bmiData[0];
result[i][3] = bmiData[1];
}
return result;
}
public static void displayTable(String[][] arr) {
System.out.printf("%-10s %-10s %-10s %-15s\n", "Height(cm)", "Weight(kg)", "BMI",
"Status");
for (String[] row : arr) {
System.out.printf("%-10s %-10s %-10s %-15s\n", row[0], row[1], row[2], row[3]);
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
double[][] hw = new double[10][2];
for (int i = 0; i < 10; i++) {
hw[i][0] = sc.nextDouble();
hw[i][1] = sc.nextDouble();
}
String[][] data = processData(hw);
displayTable(data);
}
}