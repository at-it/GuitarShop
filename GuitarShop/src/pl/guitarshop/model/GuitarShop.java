package pl.guitarshop.model;

import pl.guitarshop.exceptions.GuitarAlreadyExistsException;
import pl.guitarshop.exceptions.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class GuitarShop implements Serializable {

    private Map<String, Guitar> guitars = new HashMap<>();
    private Map<String, UserGuitarShop> users = new HashMap<>();

    public Map<String, Guitar> getGuitars() {
        return guitars;
    }

    public Map<String, UserGuitarShop> getUsers() {
        return users;
    }

    public Collection<Guitar> getSortedGuitars(Comparator<Guitar> comparator) {
        List<Guitar> list = new ArrayList<>(guitars.values());
        list.sort(comparator);
        return list;
    }

    public Optional<Guitar> findGuitarByModel(String model){
        return Optional.ofNullable(guitars.get(model));
    }

    public Collection<UserGuitarShop> getSortedUsers(Comparator<UserGuitarShop> comparator) {
        List<UserGuitarShop> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public void addGuitar(Guitar guitar) {
        if (guitars.containsKey(guitar.getModel())) {
            throw new GuitarAlreadyExistsException("Guitar already exists");
        } else {
            guitars.put(guitar.getModel(), guitar);
        }
    }

    public void addUser(UserGuitarShop user) {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            users.put(user.getEmail(), user);
        }
    }

    public boolean removeGuitar(Guitar guitar) {
        if (guitars.containsValue(guitar)) {
            guitars.remove(guitar.getModel());
            return true;
        } else {
            return false;
        }
    }
}
