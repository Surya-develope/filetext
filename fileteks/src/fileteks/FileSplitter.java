package fileteks;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file yang akan dibaca: ");
        String fileName = scanner.nextLine();
        
        System.out.print("Masukkan jumlah potongan yang diinginkan: ");
        int numParts = scanner.nextInt();
        
        Queue<String> fileParts = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            int lineCount = 0;
            
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
                lineCount++;
            }
            
            String content = contentBuilder.toString();
            int partSize = content.length() / numParts;
            int remaining = content.length() % numParts;
            int startIndex = 0;

            for (int i = 0; i < numParts; i++) {
                int endIndex = startIndex + partSize + (remaining > 0 ? 1 : 0);
                if (remaining > 0) remaining--;
                if (endIndex > content.length()) endIndex = content.length();

                String part = content.substring(startIndex, endIndex);
                fileParts.add(part);
                startIndex = endIndex;
            }
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        }

        System.out.println("\nBagian-bagian dari file:");
        while (!fileParts.isEmpty()) {
            System.out.println("Bagian: ");
            System.out.println(fileParts.poll());
            System.out.println("--------------------");
        }
        
        scanner.close();
    }
}
