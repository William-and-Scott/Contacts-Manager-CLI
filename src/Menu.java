import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Input input = new Input();
    private List<Contact> contactList = new ArrayList<>();

    public static void printMenu () {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4 or 5): ");
    }

    public void populateArraylistFromFile () {

    }

    public void updateFile () {

    }

    public void addContact () {

        String contactName = input.getString("Enter contact name: ");
        String contactNumber = input.getString("Enter contact phone number (without dashes or spaces): ");

        contactList.add(new Contact(contactName, contactNumber));

    }

    public void deleteContact (Contact contact){

    }

    public void printArrayList () {
        for (Contact contact : contactList) {
            System.out.println("Name: " + contact.getFullName() + ", Phone number: " + contact.getPhoneNumber());
        }
    }

    //accessors


    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
