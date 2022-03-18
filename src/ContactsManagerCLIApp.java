import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ContactsManagerCLIApp {

    private static final Path dataFile = Paths.get("data", "contacts.txt");

    public static void main(String[] args) {

        runProgram();
        System.out.print("                        _______________________________________");
        System.out.print("                       /                                     /|");
        System.out.print("             _________/_____________________________________/_|________");
        System.out.print("            /                                                        /|");
        System.out.print("           /                                                        / |");
        System.out.print("          /                                                        /  |");
        System.out.print("         /________________________________________________________/   |");
        System.out.print("        |                                                        |    |");
        System.out.print("        |                                                        |    |");
        System.out.print("        |     _______________________________________________    |    |");
        System.out.print("        |     |                                             |    |    |");
        System.out.print("        |     |    _____________________________________    |    |    |");
        System.out.print("        |     |    |  ☎           Main Menu            |    |    |    |");
        System.out.print("        |     |    |___________________________________|    |    |    |");
        System.out.print("        |     |    | 1. View contacts.                 |    |    |    |");
        System.out.print("        |     |    | 2. View favorites.                |    |    |    |");
        System.out.print("        |     |    | 3. Add a new contact.             |    |    |    |");
        System.out.print("        |     |    | 4. Search a contact by name.      |    |    |    |");
        System.out.print("        |     |    | 5. Delete an existing contact.    |    |    |    |");
        System.out.print("        |     |    | 6. Exit.                          |    |    |    |    a project by William and Scott");
        System.out.print("        |     |    |___________________________________|    |    |    |======================================================D=");
        System.out.print("        |     |                                             |    |    |");
        System.out.print("        |     |    Enter an option (1, 2, 3, 4, 5 or 6):    |    |    |");
        System.out.print("        |     |_____________________________________________|    |   /");
        System.out.print("        |                                                        |  /");
        System.out.print("        | ====                                                   | /");
        System.out.print("        |________________________________________________________|/");
        System.out.print("        /                                                        /|");
        System.out.print("       /     \"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"      / |");
        System.out.print("      /     ::::::::::::::::::::::::::::::::::::::::::::::     /  /");
        System.out.print("     /   ::: :::::::::::::::::::::::::::::::::::::::::: :::   /  /");
        System.out.print("    /    :::: :::::::::::::::::::::::::::::::::::::::: ::::  /  /");
        System.out.print("   /     ::::: :::::::::::::::::::::::::::::::::::::  ::::: /  /");
        System.out.print("  /      ::: ::: ::: :::::::::::::::::::: ::: ::: :::      /  /");
        System.out.print(" /________________________________________________________/  /");
        System.out.print("|_________________________________________________________|_/");

    }

    public static void runProgram () {
        createDirectoryAndFile();

        Menu menu = new Menu();

        menu.populateArraylistFromFile(dataFile);

        do {
            Menu.printMenu();
            menu.getUserChoice();
            System.out.println();
        } while (menu.isRunProgram());
        menu.updateFile(dataFile);
    }

    public static void createDirectoryAndFile() {

        Path dataDirectory = Paths.get("data");

        try {
            if (!(Files.exists(dataDirectory))) {
                Files.createDirectory(dataDirectory);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (!(Files.exists(dataFile))) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}









































