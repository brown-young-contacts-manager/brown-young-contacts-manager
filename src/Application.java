import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

// TODO: Refactor switch cases to call methods

public class Application {
    static Scanner input = new Scanner(System.in);
    static List<String> contacts;

    public static void runApplication() throws IOException {
//        Input methodInput = new Input();
        // creating directory and file for contact list
        String directory = "./src/contact/";
        String contactList = "contacts.txt";
        // creating a path to directory and file
        Path contactDir = Paths.get(directory);
        Path contactPath = Paths.get(directory, contactList);
        TreeMap<String, String> contactsMap = new TreeMap<>();
        boolean cont;

        createDirectory(contactDir);
        createFile(contactPath);


        System.out.println("Welcome to your contacts manager...");

        do {
            Menu();

            int switchInput = input.nextInt();
            input.nextLine();
            switch (switchInput) {
                case 1: // VIEW ALL
                    System.out.println();
                    getContactsList(contactPath);
                    break;
                case 2: // ADD CONTACT
                    System.out.println();
                    addContact(contactPath);
                    break;
                case 3: // SEARCH CONTACTS - NAME ONLY
                    System.out.println();
                    searchContacts(contactPath);
                    break;
                case 4: // DELETE CONTACTS
                    System.out.println();
                    deleteContact(contactPath);
                    break;
                case 5: // EXIT
                    System.out.println();
                    System.out.println("Powering off...");
                    return;
                default:
                    System.out.println();
                    System.out.println("Please select a valid option.");
            }
            System.out.println();
            System.out.println("Do you wish to continue?: [y/n]");
            cont = input.nextLine().equalsIgnoreCase("y");
            System.out.println();
        } while (cont);
        System.out.println("Powering off...");
    }

    // Create Directory if needed
    public static void createDirectory(Path contactDir) throws IOException {
        if (Files.notExists(contactDir)) {
            Files.createDirectories(contactDir);
        }
    }

    // Create file in directory if needed
    public static void createFile(Path pathInput) throws IOException {
        if (Files.notExists(pathInput)) {
            Files.createFile(pathInput);
        }
    }

    // Retrieve contacts from text file
    public static void getContactsList(Path pathInput) throws IOException {
        contacts = Files.readAllLines(pathInput);
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }

    public static void addContact(Path pathInput) throws IOException {
        System.out.println("Enter new contact first name: ");
        String first = input.nextLine();
        System.out.println("Enter new contact last name: ");
        String last = input.nextLine();
        System.out.println("Enter new contact phone number: ");
        String phoneNumber = input.nextLine();
        phoneNumber = phoneNumber.replaceAll("[^\\d]", ""); // regex: any digits 0-9
        String phoneNumberFormatted = "";
        if (phoneNumber.length() == 7) {
            phoneNumberFormatted = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
        } else if (phoneNumber.length() == 10) {
            phoneNumberFormatted = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
        }

        Contact newContact = new Contact(first, last, phoneNumberFormatted);
        String formatContact = newContact.getFirstName() + " " + newContact.getLastName() + " | " + newContact.getPhoneNumber() + " |" + System.lineSeparator();
        Files.writeString(pathInput, formatContact, StandardOpenOption.APPEND);
        System.out.println();
        System.out.println("Contact added...");
    }

    public static void searchContacts(Path pathInput) throws IOException {
        System.out.println("What is your contact's name?: ");
        String contact = input.nextLine();

        contacts = Files.readAllLines(pathInput);
        for (String line : contacts) {
            if ((line.toLowerCase()).contains((contact.toLowerCase()))) {
                System.out.println(line);
            }
        }
    }

    public static void deleteContact(Path pathInput) throws IOException {
        System.out.println("Enter contact name to delete: ");
        String deleteContact = input.nextLine();
        System.out.println("Are you sure?: [y/n]");
        String confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println(Files.readAllLines(pathInput));
        } else { Menu();}
        contacts = Files.readAllLines(pathInput);
        List<String> newContactsList = new ArrayList<>();

        for (String line : contacts) {
            if (line.contains(deleteContact)) { continue; }
            newContactsList.add(line); }
        Files.write(pathInput, newContactsList);
    }

    // Main Menu to application
    public static void Menu() {
        System.out.print("1. View contacts\n2. Add a new contact\n3. Search a contact by name\n" +
                "4. Delete an existing contact\n5. Exit\nEnter an option (1, 2, 3, 4 or 5): ");
    }

    public static void main(String[] args) throws IOException {
        runApplication();
    }
}
