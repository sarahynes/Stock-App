import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    /**
     * Returns all content from given file. If the file contains multiple lines, all lines will be
     * combined into a single string output.
     *
     * @param fileName the file name to read from
     * @return a string containing all content from the file
     * @throws FileNotFoundException when no file with given name exists
     */
    public String getFileText(String fileName) throws FileNotFoundException {

        String text = "";

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            text += scanner.nextLine();
        }

        return text;
    }
}
