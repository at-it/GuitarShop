package pl.guitarshop.io;

import pl.guitarshop.model.AcousticGuitar;
import pl.guitarshop.model.ElectricGuitar;
import pl.guitarshop.model.UserGuitarShop;

import java.time.Year;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter consolePrinter;

    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void close() {
        scanner.close();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public UserGuitarShop readAndCreateUser() {
        consolePrinter.printLine("Please provide email address: ");
        String email = scanner.nextLine();
        consolePrinter.printLine("Please provide first name: ");
        String firstName = scanner.nextLine();
        consolePrinter.printLine("Please provide last name: ");
        String lastName = scanner.nextLine();
        return new UserGuitarShop(email, firstName, lastName);
    }

    public ElectricGuitar readAndCreateElectricGuitar() {
        consolePrinter.printLine("Year:");
        Year year = Year.of(scanner.nextInt());
        consolePrinter.printLine("Number of strings:");
        byte numberOfStrings = scanner.nextByte();
        scanner.nextLine();
        consolePrinter.printLine("Brand:");
        String brand = scanner.nextLine();
        consolePrinter.printLine("Model:");
        String model = scanner.nextLine();
        consolePrinter.printLine("Scale:");
        double scale = scanner.nextDouble();
        scanner.nextLine();
        consolePrinter.printLine("Body wood:");
        String bodyWood = scanner.nextLine();
        consolePrinter.printLine("Top wood:");
        String topWood = scanner.nextLine();
        consolePrinter.printLine("Neck wood :");
        String neckWood = scanner.nextLine();
        consolePrinter.printLine("Fingerboard wood:");
        String fingerboardWood = scanner.nextLine();
        consolePrinter.printLine("Number of frets :");
        byte numberOfFrets = scanner.nextByte();
        scanner.nextLine();
        consolePrinter.printLine("Pickup Configuration :");
        String pickupConfiguration = scanner.nextLine();

        return new ElectricGuitar(year, numberOfStrings, brand, model, scale, bodyWood, topWood,
                neckWood, fingerboardWood, numberOfFrets, pickupConfiguration);
    }

    public AcousticGuitar readAndCreateAcousticGuitar() {
        consolePrinter.printLine("Year:");
        Year year = Year.of(scanner.nextInt());
        consolePrinter.printLine("Number of strings:");
        byte numberOfStrings = scanner.nextByte();
        scanner.nextLine();
        consolePrinter.printLine("Brand:");
        String brand = scanner.nextLine();
        consolePrinter.printLine("Model:");
        String model = scanner.nextLine();
        consolePrinter.printLine("Scale:");
        double scale = scanner.nextDouble();
        scanner.nextLine();
        consolePrinter.printLine("Body wood:");
        String bodyWood = scanner.nextLine();
        consolePrinter.printLine("Top wood:");
        String topWood = scanner.nextLine();
        consolePrinter.printLine("Neck wood :");
        String neckWood = scanner.nextLine();
        consolePrinter.printLine("Fingerboard wood:");
        String fingerboardWood = scanner.nextLine();
        consolePrinter.printLine("Number of frets :");
        byte numberOfFrets = scanner.nextByte();
        scanner.nextLine();
        consolePrinter.printLine("Body size :");
        String bodySize = scanner.nextLine();
        consolePrinter.printLine("Side wood :");
        String sideWood = scanner.nextLine();

        return new AcousticGuitar(year, numberOfStrings, brand, model, scale, bodyWood, topWood,
                neckWood, fingerboardWood, numberOfFrets, bodySize, sideWood);
    }
}
