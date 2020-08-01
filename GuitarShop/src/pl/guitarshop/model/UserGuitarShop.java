package pl.guitarshop.model;

import java.util.ArrayList;
import java.util.List;

public class UserGuitarShop extends User {

    private List<Guitar> orderHistory = new ArrayList<>();
    private List<Guitar> currentOrders = new ArrayList<>();

    public UserGuitarShop(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public List<Guitar> getOrderHistory() {
        return orderHistory;
    }

    public List<Guitar> getCurrentOrders() {
        return currentOrders;
    }

    @Override
    public String toCsv() {
        return getEmail() + ";" + getFirstName() + ";" + getLastName();
    }

    private void addOrderToHistory(Guitar guitar) {
        orderHistory.add(guitar);
    }

    private void addOrderToCurrent(Guitar guitar) {
        currentOrders.add(guitar);
    }

    private boolean cancelOrder(Guitar guitar) {
        boolean result = false;
        if (currentOrders.contains(guitar)) {
            currentOrders.remove(guitar);
            addOrderToHistory(guitar);
            result = true;
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserGuitarShop that = (UserGuitarShop) o;

        if (orderHistory != null ? !orderHistory.equals(that.orderHistory) : that.orderHistory != null) return false;
        return currentOrders != null ? currentOrders.equals(that.currentOrders) : that.currentOrders == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderHistory != null ? orderHistory.hashCode() : 0);
        result = 31 * result + (currentOrders != null ? currentOrders.hashCode() : 0);
        return result;
    }
}
