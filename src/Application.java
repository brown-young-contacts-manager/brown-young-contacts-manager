import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {

    public static void runApplication() {
        // creating directory and file for contact list
        String directory = "./src/contact";
        String contactList = "contacts.txt";
        // creating a path to directory and file
        Path textDirectory = Paths.get(directory);
        Path textPath = Paths.get(directory, contactList);
    }
    // Create Directory if needed
    public static void createDirectory(Path textDirectory) throws IOException {
        if(Files.notExists(textDirectory)){
            Files.createDirectories(textDirectory);
        }
    }
    // Create file in directory if needed
    public static void createFile(Path textPath) throws IOException {
        if(Files.notExists(textPath)){
            Files.createDirectories(textPath);
        }
    }

    // Main Menu to application
    public static void Menu() {
        System.out.println("1. View contacts\n2. Add a new contact\n3. Search a contact by name\n" +
                "4. Delete an existing contact\n5. Exit\nEnter an option (1, 2, 3, 4 or 5):");
    }

    public static void main(String[] args) {
        Menu();
    }
}
