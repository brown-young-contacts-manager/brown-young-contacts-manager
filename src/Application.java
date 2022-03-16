import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import util.Input;

// TODO: Refactor switch cases to call methods

public class Application {
//    static Path contactsPath = Paths.get(directory, contactList);
    static Input input = new Input();

    public static void runApplication() throws IOException {
        Scanner scanner = new Scanner(System.in);
        // creating directory and file for contact list
        String directory = "./src/contact/";
        String contactList = "contacts.txt";
        // creating a path to directory and file
        Path contactDir = Paths.get(directory);
        Path contactPath = Paths.get(directory, contactList);
        TreeMap<String, String> contactsMap = new TreeMap<>();
        List<String> contacts;
        boolean cont;

        createDirectory(contactDir);
        createFile(contactPath);


        System.out.println("Welcome to your contacts manager...");

        do {
            Menu();

            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1: // VIEW ALL
                    System.out.println();
                    getContactsList(contactPath);
                    break;
                case 2: // ADD CONTACT
                    addContact(contactPath);
                    break;
                case 3: // SEARCH CONTACTS - NAME ONLY
//                    searchContacts();
                    break;
                case 4: // DELETE CONTACTS
//                    System.out.println();
//                    System.out.println("Enter contact name to delete: ");
//                    name = scanner.nextLine();
//                    System.out.println("Are you sure?: [y/n]");
//                    String confirm = scanner.nextLine();
//                    if (confirm.equalsIgnoreCase("Y")) {
//                        System.out.println(Files.readAllLines(contactPath));
//                    } else {
//                        Menu();
//                    }
//                    contacts = Files.readAllLines(contactPath);
//
//                    List<String> newContactsList = new ArrayList<>();
//
//
//                    for (String line : contacts) {
//
//                        if (line.contains(name)) {
//                            continue;
//                        }
//                        newContactsList.add(line);
//                    }
//                    Files.write(contactPath, newContactsList);
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
            cont = scanner.next().equalsIgnoreCase("Y");
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
    public static void createFile(Path contactPath) throws IOException {
        if (Files.notExists(contactPath)) {
            Files.createFile(contactPath);
        }
    }

    // Retrieve contacts from text file
    public static void getContactsList(Path contactPath) throws IOException {
        List<String> contactList = Files.readAllLines(contactPath);
        for (String contact : contactList) {
            System.out.println(contact);
        }
    }

    public static void addContact(Path contactsPath) throws IOException {

        System.out.println();
        System.out.println("Enter new contact first name: ");
        String first = input.getString();
        System.out.println("Enter new contact last name: ");
        String last = input.getString();
        System.out.println("Enter new contact phone number:\n");
        String phoneNumber = input.getString();
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        String phoneNumberFormatted = "";
        if (phoneNumber.length() == 7) {
            phoneNumberFormatted = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
        } else if (phoneNumber.length() == 10) {
            phoneNumberFormatted = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
        }

        Contact newContact = new Contact(first, last, phoneNumberFormatted);
        String formatContact = newContact.getFirstName() + " " + newContact.getLastName() + " | " + newContact.getPhoneNumber() + " |" + System.lineSeparator();
        Files.writeString(contactsPath, formatContact, StandardOpenOption.APPEND);
        System.out.println("Contact added...");
        System.out.println();

//        int numLength = Long.toString(number).length(); // captures the int value of the string length
//        char[] oldNumArr = Long.toString(number).toCharArray(); // turns number into char array
//        StringBuilder newNumStr = new StringBuilder(); // used to set a new phone number string with "-"
//        int firstTac = 0;
//        int secondTac = 0;
//
//        switch (numLength) {
//            case 7: {
//                for (int i = 0; i < oldNumArr.length; i++) {
//                    newNumStr.append(oldNumArr[i]);
//                    firstTac++;
//                    if (firstTac == 3) {
//                        break;
//                    }
//                }
//                newNumStr.append("-");
//                for (int i = 3; i < oldNumArr.length; i++) {
//                    newNumStr.append(oldNumArr[i]);
//                }
//                contactsMap.put(name, newNumStr.toString());
//                List<String> test = List.of(name + " | " + newNumStr + " |");
//                Files.write(contactPath, test, StandardOpenOption.APPEND);
//            }
//            case 10: {
//                for (int i = 0; i < oldNumArr.length; i++) {
//                    newNumStr.append(oldNumArr[i]);
//                    firstTac++;
//                    if (firstTac == 3) {
//                        break;
//                    }
//                }
//                newNumStr.append("-");
//                for (int i = 3; i < oldNumArr.length; i++) {
//                    newNumStr.append(oldNumArr[i]);
//                    secondTac++;
//                    if (secondTac == 3) {
//                        break;
//                    }
//                }
//                newNumStr.append("-");
//                for (int i = 6; i < oldNumArr.length; i++) {
//                    newNumStr.append(oldNumArr[i]);
//                }
//                contactsMap.put(name, newNumStr.toString());
//                List<String> test = List.of(name + " | " + newNumStr + " |");
//                Files.write(contactPath, test, StandardOpenOption.APPEND);
//            }
//            default: // for some reason, always defaults WITH proper input.
//                System.out.println("Loading...");
//        }
//
//        Files.readAllLines(contactPath);
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
