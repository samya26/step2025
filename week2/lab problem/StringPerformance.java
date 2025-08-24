import java.util.*;

public class StringPerformance {
    public static long testStringConcat(int iterations) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "x";
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-15d %-15d\n", "String", (end - start), s.length());
        return end - start;
    }

    public static long testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-15d %-15d\n", "StringBuilder", (end - start), sb.length());
        return end - start;
    }

    public static long testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("x");
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s %-15d %-15d\n", "StringBuffer", (end - start), sbf.length());
        return end - start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iterations = sc.nextInt();
        System.out.printf("%-15s %-15s %-15s\n", "Method", "Time(ms)", "Length");
        testStringConcat(iterations);
        testStringBuilder(iterations);
        testStringBuffer(iterations);
    }
}
