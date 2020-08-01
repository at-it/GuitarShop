package pl.guitarshop.io.file;

import pl.guitarshop.exceptions.NoSuchFileTypeException;
import pl.guitarshop.io.ConsolePrinter;
import pl.guitarshop.io.DataReader;

public class FileManagerBuilder {
    private DataReader dataReader;
    private ConsolePrinter consolePrinter;

    public FileManagerBuilder(DataReader dataReader, ConsolePrinter consolePrinter) {
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public FileManager build() {
        consolePrinter.printLine("Choose data format");
        FileType fileType = getFileType();
        switch (fileType){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Invalid data format");
        }
    }

    private FileType getFileType() {
        boolean incorrectType = true;
        FileType result = null;
        do {
            printTypes();
            String type = dataReader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                incorrectType = false;
            } catch (IllegalArgumentException e) {
                consolePrinter.printLine("Invalid data format, choose again.");
            }
        } while (incorrectType);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            consolePrinter.printLine(value.name());
        }
    }

}
