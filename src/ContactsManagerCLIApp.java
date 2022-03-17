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
