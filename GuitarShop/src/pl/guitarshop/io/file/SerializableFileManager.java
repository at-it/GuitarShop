package pl.guitarshop.io.file;

import pl.guitarshop.exceptions.DataExportException;
import pl.guitarshop.exceptions.DataImportException;
import pl.guitarshop.model.GuitarShop;

import java.io.*;

class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "GuitarShop.o";

    @Override
    public GuitarShop importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (GuitarShop) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("No file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Error occurred during file import " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Invalid data type in file " + FILE_NAME);
        }
    }

    @Override
    public void exportData(GuitarShop guitarShop) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(guitarShop);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Error occurred during file export " + FILE_NAME);
        }
    }
}
