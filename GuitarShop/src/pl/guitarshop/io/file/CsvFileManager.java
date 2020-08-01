package pl.guitarshop.io.file;

import pl.guitarshop.exceptions.DataExportException;
import pl.guitarshop.exceptions.DataImportException;
import pl.guitarshop.exceptions.InvalidDataException;
import pl.guitarshop.model.*;

import java.io.*;
import java.time.Year;
import java.util.Collection;

class CsvFileManager implements FileManager {
    private static final String GUITARSHOP_FILE = "GuitarShop.csv";
    private static final String USERS_FILE = "Users.csv";

    @Override
    public GuitarShop importData() {
        GuitarShop guitarShop = new GuitarShop();
        importGuitars(guitarShop);
        importUsers(guitarShop);
        return guitarShop;
    }

    private void importUsers(GuitarShop guitarShop) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(guitarShop::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("File not found" + USERS_FILE);
        } catch (IOException e) {
            throw new DataImportException("File reading error" + USERS_FILE);
        }
    }

    private void importGuitars(GuitarShop guitarShop) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(GUITARSHOP_FILE))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(guitarShop::addGuitar);
        } catch (FileNotFoundException e) {
            throw new DataImportException("File not found" + GUITARSHOP_FILE);
        } catch (IOException e) {
            throw new DataImportException("File reading error" + GUITARSHOP_FILE);
        }
    }

    private UserGuitarShop createUserFromString(String line) {
        String[] split = line.split(";");
        return createUser(split);
    }

    private Guitar createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (ElectricGuitar.TYPE.equals(type)) {
            return createElectricGuitar(split);
        } else if (AcousticGuitar.TYPE.equals(type)) {
            return createAcousticGuitar(split);
        }
        throw new InvalidDataException("Unknown guitar type " + type);
    }

    private UserGuitarShop createUser(String[] data) {
        String email = data[0];
        String firstName = data[1];
        String lastName = data[2];
        return new UserGuitarShop(email, firstName, lastName);
    }

    private Guitar createElectricGuitar(String[] data) {
        Year year = Year.parse(data[1]);
        byte numberOfStrings = Byte.parseByte(data[2]);
        String brand = data[3];
        String model = data[4];
        double scale = Double.parseDouble(data[5]);
        String bodyWood = data[6];
        String topWood = data[7];
        String neckWood = data[8];
        String fingerboardWood = data[9];
        byte numberOfFrets = Byte.parseByte(data[10]);
        String pickupConfig = data[11];
        return new ElectricGuitar(year, numberOfStrings, brand, model, scale, bodyWood,
                topWood, neckWood, fingerboardWood, numberOfFrets, pickupConfig);
    }

    private Guitar createAcousticGuitar(String[] data) {
        Year year = Year.parse(data[1]);
        byte numberOfStrings = Byte.parseByte(data[2]);
        String brand = data[3];
        String model = data[4];
        double scale = Double.parseDouble(data[5]);
        String bodyWood = data[6];
        String topWood = data[7];
        String neckWood = data[8];
        String fingerboardWood = data[9];
        byte numberOfFrets = Byte.parseByte(data[10]);
        String bodySize = data[11];
        String sideWood = data[12];
        return new AcousticGuitar(year, numberOfStrings, brand, model, scale, bodyWood,
                topWood, neckWood, fingerboardWood, numberOfFrets, bodySize, sideWood);
    }

    @Override
    public void exportData(GuitarShop guitarShop) {
        exportGuitars(guitarShop);
        exportUsers(guitarShop);

    }

    private void exportUsers(GuitarShop guitarShop) {
        Collection<UserGuitarShop> users = guitarShop.getUsers().values();
        exportToCsv(users, USERS_FILE);
    }

    private void exportGuitars(GuitarShop guitarShop) {
        Collection<Guitar> guitars = guitarShop.getGuitars().values();
        exportToCsv(guitars, GUITARSHOP_FILE);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String file) {
        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (T t : collection) {
                bufferedWriter.write(t.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new DataExportException("Error occurred during users export" + file);
        }
    }
}
