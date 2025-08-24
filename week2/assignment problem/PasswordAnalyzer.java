import java.util.*;

public class PasswordAnalyzer {

    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int ascii = (int) c;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static boolean containsCommonPattern(String password) {
        String[] patterns = {"123", "abc", "qwerty", "password"};
        for (String p : patterns) {
            if (password.toLowerCase().contains(p)) return true;
        }
        return false;
    }

    public static int calculateScore(String password, int[] counts) {
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        if (containsCommonPattern(password)) score -= 15;
        return score;
    }

    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+<>?";
        String all = upper + lower + digits + special;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);
        return shuffled.toString();
    }

    public static void displayResults(String[] passwords) {
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s %-8s %-10s\n",
                "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("-------------------------------------------------------------------------------");
        for (String pwd : passwords) {
            int[] counts = analyzePassword(pwd);
            int score = calculateScore(pwd, counts);
            String strength = getStrength(score);
            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d %-8d %-10s\n",
                    pwd, pwd.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of passwords to analyze:");
        int n = sc.nextInt();
        sc.nextLine();
        String[] passwords = new String[n];
        System.out.println("Enter passwords:");
        for (int i = 0; i < n; i++) passwords[i] = sc.nextLine();
        displayResults(passwords);
        System.out.println("\nGenerate new strong password? Enter length:");
        int len = sc.nextInt();
        String newPwd = generatePassword(len);
        System.out.println("Generated Strong Password: " + newPwd);
    }
}
