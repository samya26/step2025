import java.util.*;

public class EmailAnalyzer {
    public static boolean validateEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dot = email.indexOf('.', at);
        if (at == -1 || at != lastAt || dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int at = email.indexOf('@');
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        int dot = domain.lastIndexOf('.');
        String domainName = domain.substring(0, dot);
        String extension = domain.substring(dot + 1);
        return new String[]{username, domain, domainName, extension};
    }

    public static void analyzeEmails(List<String> emails) {
        int validCount = 0, invalidCount = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        int totalUsernameLength = 0;
        for (String email : emails) {
            if (validateEmail(email)) {
                validCount++;
                String[] comp = extractComponents(email);
                totalUsernameLength += comp[0].length();
                domainCount.put(comp[1], domainCount.getOrDefault(comp[1], 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = "";
        int max = 0;
        for (String d : domainCount.keySet()) {
            if (domainCount.get(d) > max) {
                max = domainCount.get(d);
                mostCommonDomain = d;
            }
        }
        double avgUserLen = validCount == 0 ? 0 : (double) totalUsernameLength / validCount;
        System.out.println("\nAnalysis:");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + mostCommonDomain);
        System.out.println("Average Username Length: " + avgUserLen);
    }

    public static void displayResults(List<String> emails) {
        System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        for (String email : emails) {
            if (validateEmail(email)) {
                String[] c = extractComponents(email);
                System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", email, c[0], c[1], c[2], c[3], "Yes");
            } else {
                System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", email, "-", "-", "-", "-", "No");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter emails (type 'done' to finish):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            emails.add(input);
        }
        displayResults(emails);
        analyzeEmails(emails);
    }
}
