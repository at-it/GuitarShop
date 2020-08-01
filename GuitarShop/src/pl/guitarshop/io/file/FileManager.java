package pl.guitarshop.io.file;

import pl.guitarshop.model.GuitarShop;

public interface FileManager {
    GuitarShop importData();
    void exportData(GuitarShop guitarShop);
}
