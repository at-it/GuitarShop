package pl.guitarshop.app;

import pl.guitarshop.exceptions.*;
import pl.guitarshop.io.ConsolePrinter;
import pl.guitarshop.io.DataReader;
import pl.guitarshop.io.file.FileManager;
import pl.guitarshop.io.file.FileManagerBuilder;
import pl.guitarshop.model.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.InputMismatchException;

public class GuitarShopControl {
    private GuitarShop guitarShop;
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private DataReader dataReader = new DataReader(consolePrinter);
    private FileManager fileManager;

    public GuitarShopControl() {
        fileManager = new FileManagerBuilder(dataReader, consolePrinter).build();
        try {
            guitarShop = fileManager.importData();
            consolePrinter.printLine("Database imported successfully");
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("New database initialized");
            guitarShop = new GuitarShop();
        }
    }

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();

            switch (option) {
                case EXIT:
                    exit();
                    break;
                case ADD_ELECTRIC_GUITAR:
                    addElectricGuitar();
                    break;
                case ADD_ACOUSTIC_GUITAR:
                    addAcousticGuitar();
                    break;
                case PRINT_ELECTRIC_GUITARS:
                    printElectricGuitars();
                    break;
                case PRINT_ACOUSTIC_GUITARS:
                    printAcousticGuitars();
                    break;
                case DELETE_ELECTRIC_GUITAR:
                    deleteElectricGuitar();
                    break;
                case DELETE_ACOUSTIC_GUITAR:
                    deleteAcousticGuitar();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case FIND_GUITAR:
                    findGuitarByModel();
                    break;
                default:
                    consolePrinter.printLine("Option chosen is not valid");
            }
        } while (option != Option.EXIT);
    }

    private void findGuitarByModel() {
        consolePrinter.printLine("Please provide guitar model:");
        String model = dataReader.getString();
        String notFoundMessage = "Guitar model was not found";
        guitarShop.findGuitarByModel(model)
                .map(Guitar::toString)
                .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
    }

    private void addUser() {
        UserGuitarShop userGuitarShop = dataReader.readAndCreateUser();
        try {
            guitarShop.addUser(userGuitarShop);
        } catch (UserAlreadyExistsException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }

    private void printUsers() {
        Collection<UserGuitarShop> values = guitarShop.getUsers().values();
        consolePrinter.printUsers(guitarShop.getSortedUsers(
//                (u1, u2) -> u1.getLastName().compareToIgnoreCase(u2.getLastName())
                Comparator.comparing(UserGuitarShop::getLastName, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private Option getOption() {
        boolean noError = false;
        Option option = null;
        while (!noError) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                noError = true;
            } catch (NoSuchOptionException e) {
                consolePrinter.printLine((e.getMessage()));
            } catch (InputMismatchException e) {
                consolePrinter.printLine("Input provided is not a number, please correct");
            }
        }
        return option;
    }

    private void addElectricGuitar() {
        try {
            ElectricGuitar electricGuitar = dataReader.readAndCreateElectricGuitar();
            guitarShop.addGuitar(electricGuitar);
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Error during adding Electric Guitar to database");
        } catch (ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine("Maximum number of guitars reached");
        }

    }

    private void deleteElectricGuitar() {
        try {
            ElectricGuitar electricGuitar = dataReader.readAndCreateElectricGuitar();
            if (guitarShop.removeGuitar(electricGuitar)) {
                consolePrinter.printLine("Electric guitar has been removed");
            } else {
                consolePrinter.printLine("Electric guitar was not found");
            }
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Deletion not successful, electric guitar was not found");
        }
    }

    private void addAcousticGuitar() {
        try {
            AcousticGuitar acousticGuitar = dataReader.readAndCreateAcousticGuitar();
            guitarShop.addGuitar(acousticGuitar);
        } catch (
                InputMismatchException e) {
            consolePrinter.printLine("Error during adding Acoustic Guitar to database");
        } catch (
                ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine("Maximum number of guitars reached");
        }

    }

    private void deleteAcousticGuitar() {
        try {
            AcousticGuitar acousticGuitar = dataReader.readAndCreateAcousticGuitar();
            if (guitarShop.removeGuitar(acousticGuitar)) {
                consolePrinter.printLine("Acoustic guitar has been removed");
            } else {
                consolePrinter.printLine("Acoustic guitar was not found");
            }
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Deletion not successful, acoustic guitar was not found");
        }
    }

    private void exit() {
        try {
            fileManager.exportData(guitarShop);
            consolePrinter.printLine("Export of the data successfully finished");
        } catch (DataExportException e) {
            consolePrinter.printLine(e.getMessage());
        }
        consolePrinter.printLine("Program finished");
        dataReader.close();
    }

    private void printElectricGuitars() {
        consolePrinter.printElectricGuitars(guitarShop.getSortedGuitars(
//                (g1, g2) -> g1.getBrand().compareToIgnoreCase(g2.getBrand())
                Comparator.comparing(Guitar::getBrand, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void printAcousticGuitars() {
        consolePrinter.printAcousticGuitars(guitarShop.getSortedGuitars(
//                (g1, g2) -> g1.getBrand().compareToIgnoreCase(g2.getBrand())
                Comparator.comparing(Guitar::getBrand, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void printOptions() {
        consolePrinter.printLine("Choose an option:");
        for (Option option : Option.values()) {
            consolePrinter.printLine(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "Exit from Guitar Shop"),
        ADD_ELECTRIC_GUITAR(1, "Add electric guitar to the collection"),
        ADD_ACOUSTIC_GUITAR(2, "Add acoustic guitar to the collection"),
        PRINT_ELECTRIC_GUITARS(3, "Show electric guitars collection"),
        PRINT_ACOUSTIC_GUITARS(4, "Show acoustic guitars collection"),
        DELETE_ELECTRIC_GUITAR(5, "Delete electric guitar"),
        DELETE_ACOUSTIC_GUITAR(6, "Delete acoustic guitar"),
        ADD_USER(7, "Add new user"),
        PRINT_USERS(8, "Print users"),
        FIND_GUITAR(9, "Find guitar by model");

        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("ID of such option does not exist" + option);
            }
        }
    }
}
