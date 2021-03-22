package capitaly.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    public Board(final String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }
}
