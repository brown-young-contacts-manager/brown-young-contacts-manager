import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void runApplication() throws IOException {
        Scanner scanner = new Scanner(System.in);
        // creating directory and file for contact list
        String directory = "./src/contact";
        String contactList = "contacts.txt";
        // creating a path to directory and file
        Path textDirectory = Paths.get(directory);
        Path textPath = Paths.get(directory, contactList);
        List<String> contacts;

        createDirectory(textDirectory);
        createFile(textPath);

        System.out.println("Welcome....");
            Menu();

            int input = scanner.nextInt();
            scanner.nextLine();
            switch(input){
                case 1:
                    System.out.println("placeholder for contacts");
                    break;
                case 2:
                    System.out.println("Enter new contact name: ");
                    String n = scanner.nextLine();

                    System.out.printf("Enter %s phone number:\n", n);
                    long phoneInput = scanner.nextLong();
                    contacts = Arrays.asList(n + " | " + phoneInput);
                    Files.write(textPath, contacts, StandardOpenOption.APPEND);
                    break;
                case 3:
                    System.out.println("What is your contact's name?: ");
                    n = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Enter contact name to delete: ");
                    n = scanner.nextLine();
                    System.out.println("Are you sure?: [y/n]");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")){
                        // delete the contact
                    }
                    else {
                        Menu();
                    }
                    break;
                case 5:
                    System.out.println("powering off...");
                    return;
                default:
                    System.out.println("Invalid input!");
                    Menu();
            }
    }

    // Create Directory if needed
    public static void createDirectory(Path textDirectory) throws IOException {
        if (Files.notExists(textDirectory)) {
            Files.createDirectories(textDirectory);
        }
    }

    // Create file in directory if needed
    public static void createFile(Path textPath) throws IOException {
        if (Files.notExists(textPath)) {
            Files.createFile(textPath);
        }
    }

    // Main Menu to application
    public static void Menu() {
        System.out.println("1. View contacts\n2. Add a new contact\n3. Search a contact by name\n" +
                "4. Delete an existing contact\n5. Exit\nEnter an option (1, 2, 3, 4 or 5):");
    }

    public static void main(String[] args) throws IOException {
        runApplication();
    }
}
