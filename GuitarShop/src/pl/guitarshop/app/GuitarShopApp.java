package pl.guitarshop.app;

class GuitarShopApp {
    private static final String APP_NAME = "Guitar Data Base v2.0";

    public static void main(String[] args) {
        System.out.println(APP_NAME);
        GuitarShopControl guitarShopControl = new GuitarShopControl();
        guitarShopControl.controlLoop();
    }
}
