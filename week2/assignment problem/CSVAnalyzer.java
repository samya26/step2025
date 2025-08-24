import java.util.*;

public class CSVAnalyzer {
    static List<List<String>> parseCSV(String input) {
        List<List<String>> data = new ArrayList<>();
        int i=0,n=input.length();
        List<String> row = new ArrayList<>();
        while (i<n) {
            if (row==null) row=new ArrayList<>();
            if (input.charAt(i)=='"') {
                int j=i+1;
                StringBuilder sb=new StringBuilder();
                while (j<n) {
                    if (input.charAt(j)=='"' && (j+1==n || input.charAt(j+1)==',' || input.charAt(j+1)=='\n')) {
                        i=j+1;
                        break;
                    }
                    sb.append(input.charAt(j));
                    j++;
                }
                row.add(sb.toString());
            } else {
                int j=i;
                while (j<n && input.charAt(j)!=',' && input.charAt(j)!='\n') j++;
                row.add(input.substring(i,j));
                i=j;
            }
            if (i<n && input.charAt(i)==',') i++;
            if (i<n && input.charAt(i)=='\n') {
                data.add(row);
                row=new ArrayList<>();
                i++;
            }
        }
        if (!row.isEmpty()) data.add(row);
        return data;
    }

    static boolean isNumeric(String s) {
        if (s==null || s.isEmpty()) return false;
        int dot=0;
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if (c=='.') dot++;
            else if (c<'0'||c>'9') return false;
        }
        return dot<=1;
    }

    static List<List<String>> clean(List<List<String>> data) {
        List<List<String>> cleaned=new ArrayList<>();
        for (List<String> row:data) {
            List<String> newRow=new ArrayList<>();
            for (String f:row) {
                if (f==null) f="";
                newRow.add(f.trim());
            }
            cleaned.add(newRow);
        }
        return cleaned;
    }

    static void analyze(List<List<String>> data) {
        if (data.isEmpty()) return;
        int cols=data.get(0).size();
        Map<Integer,List<Double>> numeric=new HashMap<>();
        Map<Integer,Set<String>> categorical=new HashMap<>();
        int missing=0;
        for (int j=0;j<cols;j++) {
            numeric.put(j,new ArrayList<>());
            categorical.put(j,new HashSet<>());
        }
        for (List<String> row:data) {
            for (int j=0;j<cols;j++) {
                if (j>=row.size()) { missing++; continue; }
                String v=row.get(j);
                if (v.isEmpty()) { missing++; continue; }
                if (isNumeric(v)) numeric.get(j).add(Double.parseDouble(v));
                else categorical.get(j).add(v);
            }
        }
        for (int j=0;j<cols;j++) {
            List<Double> nums=numeric.get(j);
            if (!nums.isEmpty()) {
                double min=nums.get(0),max=nums.get(0),sum=0;
                for (double d:nums) { if (d<min) min=d; if (d>max) max=d; sum+=d; }
                double avg=sum/nums.size();
                System.out.printf("Column %d (Numeric) -> Min: %.2f Max: %.2f Avg: %.2f%n",j+1,min,max,avg);
            } else if (!categorical.get(j).isEmpty()) {
                System.out.printf("Column %d (Categorical) -> Unique values: %d%n",j+1,categorical.get(j).size());
            }
        }
        int total=data.size()*cols;
        double completeness=100.0*(total-missing)/total;
        System.out.printf("Data Completeness: %.2f%%%n",completeness);
    }

    static void formatTable(List<List<String>> data) {
        if (data.isEmpty()) return;
        int cols=0;
        for (List<String> row:data) if (row.size()>cols) cols=row.size();
        int[] width=new int[cols];
        for (List<String> row:data) {
            for (int j=0;j<row.size();j++) {
                width[j]=Math.max(width[j],row.get(j).length());
            }
        }
        StringBuilder sb=new StringBuilder();
        for (List<String> row:data) {
            sb.append("|");
            for (int j=0;j<cols;j++) {
                String v=j<row.size()?row.get(j):"";
                sb.append(String.format(" %-" + width[j] + "s |",v));
            }
            sb.append("\n");
