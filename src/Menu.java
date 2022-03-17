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
                runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
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
            String contactNumber = phoneNumberValidator();





            for (Contact contact : contactList) {
                if (contact.getFullName().equalsIgnoreCase(contactName)) {
                    if (input.yesNo("This entry already exists, would you like to overwrite it? (y/n): ")) {
                    contact.setFullName(contactName);
                    contact.setPhoneNumber(contactNumber);
                }
                    return;
                }
            }
            contactList.add(new Contact(contactName, contactNumber));

//                else
//            }

            if (!(input.yesNo("Make another contact?"))) {
                break;
            }
        } while (true);


        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
    }
    //dual purpose, could search and delete
    public boolean searchContact () {
        String searchedName = input.getString("Enter a contacts name: ");
        int count = 0;

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFullName().toLowerCase().contains(searchedName.toLowerCase())) {
                count++;
                System.out.println((i+1) + ". Name: " + contactList.get(i).getFullName() + ", Phone number: " + contactList.get(i).getPhoneNumber());
            }
        }
        if (count == 0) {
            System.out.println("Could not find contact with that name.");
            return false;
        }
        return true;
    }
    // TODO: the delete method will still ask for a contact to delete even if the search method couldnt find any contacts with that name.
    public void deleteContact () {
        if (searchContact()){
            int userDeleteChoice = input.getInt("Enter number of contact you want to delete: ");
            contactList.remove(userDeleteChoice - 1);
        }
        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
    }

    public void printArrayList () {
        String nameColumn = "Name";
        String numColumn = "Phone Number";
        System.out.printf("%-20s | %12s |\n", nameColumn, numColumn);
//        String dash = "-";
        for (int i = 0; i < 37; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Contact contact : contactList) {
            System.out.printf("%-20s | %-12s |\n", contact.getFullName(), formatNumber(contact.getPhoneNumber()));
        }

        runProgram = input.yesNo("Do you want to make another selection? (y/n) ");
    }

    public String formatNumber (String number) {
        String dash = "-";
        if (number.length() == 7) {
           return number.substring(0,3) + dash + number.substring(3);
        } else {
            return number.substring(0,3) + dash + number.substring(3,6) + dash + number.substring(6);
        }
    }

    public String phoneNumberValidator () {
        while (true) {
            String phoneNumber = String.valueOf(input.getInt("Enter contact phone number (without dashes or spaces): "));

            if (phoneNumber.length() == 7 || phoneNumber.length() == 10) {
                return phoneNumber;
            }
            System.out.println("Phone number must be 7 or 10 digits long!");
        }

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
