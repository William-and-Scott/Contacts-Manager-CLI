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

    public static void printMenu () {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4 or 5): ");
    }

    public void getUserChoice () {
        int userChoice = input.getInt(1, 5);

        switch (userChoice) {
            case 1 :
                printArrayList();
                break;
            case 2 :
                addContact();
                break;
            case 3 :
                searchContact();
                break;
            case 4 :
                deleteContact();
                break;
            default:
                runProgram = false;
                break;
        }
    }

    public void populateArraylistFromFile (Path dataFile) {
        try {
            List<String> tempContactList = Files.readAllLines(dataFile);
            for (int i = 0; i < tempContactList.size(); i++) {
                String [] chopString = tempContactList.get(i).split(" , ");
                contactList.add(new Contact(chopString[0], chopString[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFile (Path dataFile) {
        try {
            List<String> writeContactsToFile = new ArrayList<>();
            for(Contact contact : contactList) {
                writeContactsToFile.add(toString(contact));
            }
            Files.write(dataFile, writeContactsToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString (Contact contact) {
        return contact.getFullName() + " , " + contact.getPhoneNumber();
    }

    public void addContact () {

        do {
            String contactName = input.getString("Enter contact name: ");
            String contactNumber = input.getString("Enter contact phone number (without dashes or spaces): ");

            contactList.add(new Contact(contactName, contactNumber));

            if (!(input.yesNo("Make another contact?"))) {
                break;
            }
        } while (true);


        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
    }
    //dual purpose, could search and delete
    public void searchContact () {
        String searchedName = input.getString("Enter a contacts name: ");
        int count = 0;

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFullName().contains(searchedName)) {
                count++;
                System.out.println((i+1) + ". Name: " + contactList.get(i).getFullName() + ", Phone number: " + contactList.get(i).getPhoneNumber());
            }
        }
        if (count == 0) {
            System.out.println("Could not find contact with that name.");
        }
    }

    public void deleteContact () {
        searchContact();

        int userDeleteChoice = input.getInt("Enter number of contact you want to delete: ");
        contactList.remove(userDeleteChoice - 1);

        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
    }

    public void printArrayList () {
        for (Contact contact : contactList) {
            System.out.println("Name: " + contact.getFullName() + ", Phone number: " + contact.getPhoneNumber());
        }

        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
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
