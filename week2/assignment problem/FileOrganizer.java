import java.util.*;

public class FileOrganizer {
    static class FileInfo {
        String originalName;
        String name;
        String extension;
        String category;
        String newName;
        boolean valid;
        String subCategory;
        int priority;
    }

    static FileInfo extractFile(String file) {
        FileInfo fi = new FileInfo();
        fi.originalName = file;
        int dot = file.lastIndexOf('.');
        if (dot == -1 || dot == 0 || dot == file.length()-1) {
            fi.valid = false;
            fi.name = file;
            fi.extension = "";
        } else {
            fi.valid = true;
            fi.name = file.substring(0,dot);
            fi.extension = file.substring(dot+1).toLowerCase();
        }
        return fi;
    }

    static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("mkv")) return "Video";
        if (ext.equals("pdf")) return "PDF";
        return "Unknown";
    }

    static void assignCategories(List<FileInfo> files) {
        for (FileInfo fi:files) {
            if (!fi.valid) fi.category="Invalid";
            else fi.category=categorize(fi.extension);
        }
    }

    static void generateNewNames(List<FileInfo> files) {
        Map<String,Integer> counter=new HashMap<>();
        String date="2025-08-24";
        for (FileInfo fi:files) {
            if (!fi.valid) { fi.newName="INVALID"; continue; }
            String base=fi.category+"_"+date;
            int c=counter.getOrDefault(base,0)+1;
            counter.put(base,c);
            fi.newName=base+"_"+c+"."+fi.extension;
        }
    }

    static void analyzeContent(List<FileInfo> files, Map<String,String> mockContent) {
        for (FileInfo fi:files) {
            if (fi.extension.equals("txt") && mockContent.containsKey(fi.originalName)) {
                String content=mockContent.get(fi.originalName).toLowerCase();
                if (content.contains("resume")) fi.subCategory="Resume";
                else if (content.contains("report")) fi.subCategory="Report";
                else if (content.contains("class")||content.contains("public")) fi.subCategory="Code";
                else fi.subCategory="General";
                if (fi.originalName.toLowerCase().contains("urgent")) fi.priority=1;
                else fi.priority=2;
            } else fi.subCategory="N/A";
        }
    }

    static void report(List<FileInfo> files) {
        System.out.println("File Organization Report:");
        System.out.printf("%-20s %-12s %-25s %-12s %-8s%n","Original","Category","New Name","SubCat","Priority");
        for (FileInfo fi:files) {
            System.out.printf("%-20s %-12s %-25s %-12s %-8d%n",fi.originalName,fi.category,fi.newName,fi.subCategory,fi.priority);
        }
        Map<String,Integer> counts=new HashMap<>();
        for (FileInfo fi:files) {
            counts.put(fi.category,counts.getOrDefault(fi.category,0)+1);
        }
        System.out.println("\nCategory Counts:");
        for (String c:counts.keySet()) {
            System.out.println(c+" -> "+counts.get(c));
        }
    }

    static void batchCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo fi:files) {
            if (fi.valid && !fi.newName.equals("INVALID")) {
                System.out.println("re
