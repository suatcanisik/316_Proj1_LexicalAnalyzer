import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

//eeeeee
public class Main {
    public static void main(String[] args) {

        try {
            File file = new File("C:\\Users\\suatc\\IdeaProjects\\Annie_thomson_Proj1\\src\\test2.pas");
            LexicalAnalyzer la = new LexicalAnalyzer(file);
            la.output();

        } catch (FileNotFoundException e) {
            System.out.println("Error: Cant read the input file!");
            e.printStackTrace();
        }

    }
}
