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
        boolean cont;

        createDirectory(textDirectory);
        createFile(textPath);

        System.out.println("Welcome....");

        do {
            Menu();

            int input = scanner.nextInt();
            scanner.nextLine();
            switch(input){
                case 1:
                    System.out.println("placeholder for contacts");
                    break;
                case 2: // ADD CONTACT
                    System.out.println("Enter new contact name: ");
                    String name = scanner.nextLine();

                    System.out.printf("Enter %s phone number:\n", name);
                    long phone = scanner.nextLong();
                    contacts = Arrays.asList(name + " | " + phone);
                    Files.write(textPath, contacts, StandardOpenOption.APPEND);
                    break;
                case 3: // SEARCH CONTACTS - NAME ONLY
                    System.out.println("What is your contact's name?: ");
                    name = scanner.nextLine();
                    break;
                case 4: // DELETE CONTACTS
                    System.out.println("Enter contact name to delete: ");
                    name = scanner.nextLine();
                    System.out.println("Are you sure?: [y/n]");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")){
                        // delete the contact
                        System.out.println(Files.readAllLines(textPath));
                    }
                    else {
                        Menu();
                    }
                        contacts = Files.readAllLines(textPath);
                        //Second: Let's set up a second array list to hold the updated list we want to write to the text file
                        List<String> newContactsList = new ArrayList<>();


                        //In English : Look inside of ONE LINE from ALL OF THESE LINES I got from my text file
                        for (String line : contacts){

                            if(line.contains(name)){
                                newContactsList.remove(name);
                                continue;
                            }

                            newContactsList.add(line);

                        }

//                        System.out.println("printGroceryList2 = " + contacts);
//                        System.out.println("newContactsList = " + newContactsList);
//                        contacts = newContactsList;
//                        System.out.println("printGroceryList2 (overwrite experiment) = " + contacts);

                        Files.write(textPath, newContactsList);
                    break;
                case 5: // EXIT
                    return;
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println("Do you wish to continue?: [y/n]");
            cont = scanner.next().equalsIgnoreCase("Y");
        } while (cont);
        System.out.println("Powering off...");
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
