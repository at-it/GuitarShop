package pl.guitarshop.model;

import java.time.Year;

public class ElectricGuitar extends Guitar {
    public static final String TYPE = "Electric Guitar";
    private String pickupConfig;

    public ElectricGuitar(Year year, byte numberOfStrings, String brand, String model, double scale, String bodyWood,
                          String topWood, String neckWood, String fingerboardWood, byte numberOfFrets,
                          String pickupConfig) {
        super(year, numberOfStrings, brand, model, scale, bodyWood, topWood, neckWood, fingerboardWood, numberOfFrets);
        this.pickupConfig = pickupConfig;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Pickup configuration: " + pickupConfig;
    }

    @Override
    public String toCsv() {
        return TYPE + ";" + getYear() + ";" + getNumberOfStrings() + ";" + getBrand() + ";" + getModel() + ";" +
                getScale() + ";" + getBodyWood() + ";" + getTopWood() + ";" + getNeckWood() + ";" +
                getFingerboardWood() + ";" + getNumberOfFrets() + ";" + pickupConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ElectricGuitar that = (ElectricGuitar) o;

        return pickupConfig != null ? pickupConfig.equals(that.pickupConfig) : that.pickupConfig == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pickupConfig != null ? pickupConfig.hashCode() : 0);
        return result;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getPickupConfig() {
        return pickupConfig;
    }

    public void setPickupConfig(String pickupConfig) {
        this.pickupConfig = pickupConfig;
    }
}
