package pl.guitarshop.io;

import pl.guitarshop.model.AcousticGuitar;
import pl.guitarshop.model.ElectricGuitar;
import pl.guitarshop.model.Guitar;
import pl.guitarshop.model.UserGuitarShop;

import java.util.Collection;

public class ConsolePrinter {

    public void printElectricGuitars(Collection<Guitar> guitars) {
        long counter = guitars.stream()
                .filter(guitar -> guitar instanceof ElectricGuitar)
                .map(Guitar::toString)
                .peek(this::printLine)
                .count();
        if (counter == 0) {
            printLine("No electric guitars in Guitar Data Base");
        }
    }

    public void printAcousticGuitars(Collection<Guitar> guitars) {
        long counter = guitars.stream()
                .filter(guitar -> guitar instanceof AcousticGuitar)
                .map(Guitar::toString)
                .peek(this::printLine)
                .count();
        if (counter == 0) {
            printLine("No acoustic guitars in Guitar Data Base");
        }
    }

    public void printUsers(Collection<UserGuitarShop> users) {
        for (UserGuitarShop user : users) {
            printLine(user);
        }
    }

    public <E> void printLine(E e) {
        System.out.println(e);
    }

}
