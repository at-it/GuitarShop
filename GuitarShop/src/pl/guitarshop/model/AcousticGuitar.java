package pl.guitarshop.model;

import java.time.Year;

public class AcousticGuitar extends Guitar {
    public static final String TYPE = "Acoustic Guitar";
    private String bodySize;
    private String sideWood;

    public AcousticGuitar(Year year, byte numberOfStrings, String brand, String model, double scale, String bodyWood,
                          String topWood, String neckWood, String fingerboardWood, byte numberOfFrets,
                          String bodySize, String sideWood) {
        super(year, numberOfStrings, brand, model, scale, bodyWood, topWood, neckWood, fingerboardWood, numberOfFrets);
        this.bodySize = bodySize;
        this.sideWood = sideWood;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Bodysize: " + bodySize + "\n" + "Sidewood: " + sideWood;
    }

    @Override
    public String toCsv() {
        return TYPE + ";" + getYear() + ";" + getNumberOfStrings() + ";" + getBrand() + ";" + getModel() + ";" +
                getScale() + ";" + getBodyWood() + ";" + getTopWood() + ";" + getNeckWood() + ";" +
                getFingerboardWood() + ";" + getNumberOfFrets() + ";" + bodySize + ";" + sideWood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AcousticGuitar that = (AcousticGuitar) o;

        if (bodySize != null ? !bodySize.equals(that.bodySize) : that.bodySize != null) return false;
        return sideWood != null ? sideWood.equals(that.sideWood) : that.sideWood == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bodySize != null ? bodySize.hashCode() : 0);
        result = 31 * result + (sideWood != null ? sideWood.hashCode() : 0);
        return result;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getBodySize() {
        return bodySize;
    }

    public void setBodySize(String bodySize) {
        this.bodySize = bodySize;
    }

    public String getSideWood() {
        return sideWood;
    }

    public void setSideWood(String sideWood) {
        this.sideWood = sideWood;
    }
}
