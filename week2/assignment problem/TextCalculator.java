import java.util.*;

public class TextCalculator {
    static boolean isDigit(char c) { return c >= '0' && c <= '9'; }
    static boolean isOp(char c) { return c=='+'||c=='-'||c=='*'||c=='/'; }
    static boolean isAllowed(char c) { return isDigit(c)||isOp(c)||c=='('||c==')'||c==' '; }

    static boolean isValid(String expr) {
        for (int i=0;i<expr.length();i++) if (!isAllowed(expr.charAt(i))) return false;
        String s = expr.replaceAll("\\s+","");
        int bal = 0;
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c=='(') bal++;
            if (c==')') { bal--; if (bal<0) return false; }
        }
        if (bal!=0) return false;
        if (s.isEmpty()) return false;
        int n = s.length();
        for (int i=0;i<n;i++) {
            char c = s.charAt(i);
            if (isOp(c)) {
                if (i==n-1) return false;
                char nxt = s.charAt(i+1);
                if (c=='-' && (i==0 || s.charAt(i-1)=='(' || isOp(s.charAt(i-1)))) {
                    int j=i+1;
                    if (j<n && s.charAt(j)=='(') return false;
                    while (j<n && isDigit(s.charAt(j))) j++;
                    if (j==i+1) return false;
                } else {
                    if (i==0) return false;
                    if (isOp(s.charAt(i-1)) || s.charAt(i-1)=='(') return false;
                    if (!(isDigit(nxt) || nxt=='(' || (nxt=='-' && i+2<n && isDigit(s.charAt(i+2))))) return false;
                }
            }
            if (c==')') {
                if (i>0 && isOp(s.charAt(i-1))) return false;
                if (i<n-1 && s.charAt(i+1)=='(') return false;
            }
            if (c=='(') {
                if (i<n-1 && isOp(s.charAt(i+1)) && s.charAt(i+1)!='-') return false;
                if (i>0 && isDigit(s.charAt(i-1))) return false;
            }
        }
        return true;
    }

    static long parseNumber(String s, int[] idx) {
        int i = idx[0];
        long sign = 1;
        if (i < s.length() && s.charAt(i)=='-') { sign=-1; i++; }
        long val = 0;
        boolean any=false;
        while (i < s.length() && isDigit(s.charAt(i))) {
            any=true;
            val = val*10 + (s.charAt(i)-'0');
            i++;
        }
        if (!any) throw new RuntimeException("Invalid number");
        idx[0]=i;
        return sign*val;
    }

    static String render(List<Long> nums, List<Character> ops) {
        StringBuilder sb = new StringBuilder();
        if (nums.isEmpty()) return "";
        sb.append(nums.get(0));
        for (int i=0;i<ops.size();i++) {
            sb.append(' ').append(ops.get(i)).append(' ').append(nums.get(i+1));
        }
        return sb.toString();
    }

    static long evalFlat(String s, StringBuilder steps) {
        String expr = s.replaceAll("\\s+","");
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int[] idx = {0};
        while (idx[0] < expr.length()) {
            char c = expr.charAt(idx[0]);
            if (isDigit(c) || c=='-') {
                long num = parseNumber(expr, idx);
                nums.add(num);
            } else if (isOp(c)) {
                ops.add(c);
                idx[0]++;
            } else {
                throw new RuntimeException("Unexpected");
            }
        }
        for (int i=0;i<ops.size();) {
            char op = ops.get(i);
            if (op=='*' || op=='/') {
                long a = nums.get(i), b = nums.get(i+1);
                if (op=='/' && b==0) throw new ArithmeticException("Division by zero");
                long r = (op=='*') ? a*b : a/b;
                nums.set(i, r);
                nums.remove(i+1);
                ops.remove(i);
                steps.append(render(nums, ops)).append("\n");
            } else i++;
        }
        while (!ops.isEmpty()) {
            long a = nums.get(0), b = nums.get(1);
            char op = ops.get(0);
            long r = (op=='+') ? a+b : a-b;
            nums.set(0, r);
            nums.remove(1);
            ops.remove(0);
            steps.append(render(nums, ops)).append("\n");
        }
        return nums.get(0);
    }

    static long eval(String s, StringBuilder steps) {
        String expr = s;
        steps.append(expr).append("\n");
        while (expr.contains("(")) {
            int l = expr.lastIndexOf('(');
            int r = expr.indexOf(')', l);
            if (r<0) throw new RuntimeException("Unmatched parentheses");
            String inside = expr.substring(l+1, r);
            StringBuilder innerSteps = new StringBuilder();

