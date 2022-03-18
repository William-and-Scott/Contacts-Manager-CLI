import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Input input = new Input();
    private List<Contact> contactList = new ArrayList<>();
    private boolean runProgram = true;

    public static final String ANSI_GREEN_TEXT = "\033[1;32m";

    public static void printMenu() {
        System.out.println(ANSI_GREEN_TEXT);

        System.out.println("_____________________________________");
        System.out.println("|  ☎           Main Menu            |");
        System.out.println("|___________________________________|");
        System.out.println("| 1. View contacts.                 |");
        System.out.println("| 2. View favorites.                |");
        System.out.println("| 3. Add a new contact.             |");
        System.out.println("| 4. Search a contact by name.      |");
        System.out.println("| 5. Delete an existing contact.    |");
        System.out.println("| 6. Exit.                          |");
        System.out.println("|___________________________________|\n");
        System.out.print("Enter an option (1, 2, 3, 4, 5 or 6): ");

    }

    public void getUserChoice() {
        int userChoice = input.getInt(1, 6);

        switch (userChoice) {
            case 1:
                printArrayList();
                break;
            case 2:
                printFavoriteList();
                break;
            case 3:
                addContact();
                break;
            case 4:
                searchContact();
                runProgram = input.yesNo("Return to main menu? (y/n): ");
                break;
            case 5:
                deleteContact();
                break;
            default:
                runProgram = false;
                break;
        }
    }



    public void populateArraylistFromFile(Path dataFile) {
        try {
            List<String> tempContactList = Files.readAllLines(dataFile);
            for (int i = 0; i < tempContactList.size(); i++) {
                String[] chopString = tempContactList.get(i).split(" , ");
                contactList.add(new Contact(chopString[0], chopString[1], chopString[2]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFile(Path dataFile) {
        try {
            List<String> writeContactsToFile = new ArrayList<>();
            for (Contact contact : contactList) {
                writeContactsToFile.add(toString(contact));
            }
            Files.write(dataFile, writeContactsToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(Contact contact) {
        return contact.getFullName() + " , " + contact.getPhoneNumber() + " , " + contact.isFavorite();
    }

    public void addContact() {

        do {
            String contactName = fullNameValidator();
            String contactNumber = phoneNumberValidator();
            String isFavorite = isFavoriteValidator();


            for (Contact contact : contactList) {
                if (contact.getFullName().equalsIgnoreCase(contactName)) {
                    if (input.yesNo("This entry already exists, would you like to overwrite it? (y/n): ")) {
                        contact.setFullName(contactName);
                        contact.setPhoneNumber(contactNumber);
                        contact.setFavorite(isFavorite);
                    }
                    return;
                }
            }
            contactList.add(new Contact(contactName, contactNumber, isFavorite));

            if (!(input.yesNo("Would you like to create another contact? (y/n): "))) {
                break;
            }
        } while (true);


        runProgram = input.yesNo("Return to main menu? (y/n): ");
    }

    //dual purpose, could search and delete
    public boolean searchContact() {

        String searchedName = input.getString("Enter a contacts name: ");

        int count = 0;
        String nameColumn = "Name";
        String numColumn = "Phone Number";
        String favColumn = "Favorite";
        for (int i = 0; i < 56; i++) {
            System.out.print("_");
        }
        System.out.printf("\n| %-26s | %12s | %8s |\n", nameColumn, numColumn, favColumn);

        System.out.print("|");
        for (int j = 0; j < 54; j++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFullName().toLowerCase().contains(searchedName.toLowerCase())) {
                count++;
                System.out.printf("| %s. %-23s | %-12s | %8s |\n", i+1, contactList.get(i).getFullName(), formatNumber(contactList.get(i).getPhoneNumber()), contactList.get(i).isFavorite());
            }
        }

        for (int i = 0; i < 56; i++) {
            System.out.print("-");
        }
        System.out.println();


        if (count == 0) {
            System.out.println("Could not find contact with that name.");
            return false;
        }
        return true;
    }

    public void deleteContact() {
        if (searchContact()) {
            int userDeleteChoice = input.getInt("Enter number of the contact you want to delete (0 if you want to abort delete process): ");
            if (!(userDeleteChoice == 0)) {
                contactList.remove(userDeleteChoice - 1);
            }
        }
        runProgram = input.yesNo("Return to main menu? (y/n): ");
    }

    public void printArrayList() {
        String nameColumn = "Name";
        String numColumn = "Phone Number";
        String favColumn = "Favorite";


        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }

        System.out.printf("\n| %-20s | %12s | %8s |\n", nameColumn, numColumn, favColumn);

        System.out.print("|");
        for (int i = 0; i < 48; i++) {
            System.out.print("-");
        }
        System.out.println("|");
        for (Contact contact : contactList) {
            System.out.printf("| %-20s | %-12s | %-8s |\n", contact.getFullName(), formatNumber(contact.getPhoneNumber()), contact.isFavorite());
        }

        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
        runProgram = input.yesNo("Return to main menu? (y/n): ");
    }

    private void printFavoriteList() {
        int count = 0;
        String nameColumn = "Name";
        String numColumn = "Phone Number";
        String favColumn = "Favorite";
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.printf("\n| %-20s | %12s | %8s |\n", nameColumn, numColumn, favColumn);

        System.out.print("|");
        for (int j = 0; j < 48; j++) {
            System.out.print("-");
        }
        System.out.println("|");
        for (Contact contact : contactList) {
            if (contact.getFavorite().contains("true")) {
                count++;
                System.out.printf("| %-20s | %-12s | %-8s |\n", contact.getFullName(), formatNumber(contact.getPhoneNumber()), contact.getFavorite());
            }
        }
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
        if (count == 0) {
            System.out.println("Could not find any favorites.");
        }
        runProgram = input.yesNo("Return to main menu? (y/n): ");
    }

    public String formatNumber(String number) {
        String dash = "-";
        if (number.length() == 7) {
            return number.substring(0, 3) + dash + number.substring(3);
        } else {
            return number.substring(0, 3) + dash + number.substring(3, 6) + dash + number.substring(6);
        }
    }

    public String phoneNumberValidator() {
        while (true) {
            String phoneNumber = String.valueOf(input.getLong("Enter contact phone number (without dashes or spaces): "));

            if (phoneNumber.length() == 7 || phoneNumber.length() == 10) {
                return phoneNumber;
            }
            System.out.println("Phone number must be 7 or 10 digits long!");
        }
    }

    public String fullNameValidator() {
        while (true) {
            String fullName = input.getString("Enter full name: ");

            if (fullName.length() > 0 && fullName.length() <= 20) {
                return fullName;
            }
            if (fullName.length() == 0) {
                System.out.println("Name cannot be empty.");
            } else System.out.println("Max 20 character, please abbreviate your first name.");
        }
    }

    public String isFavoriteValidator() {
        if (input.yesNo("Is this contact a favorite? (y/n): ")) {
            return "true";
        }
        return "false";
    }

    //accessors

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public boolean isRunProgram() {
        return runProgram;
    }
}
