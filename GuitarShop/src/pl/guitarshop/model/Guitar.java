package pl.guitarshop.model;

import java.io.Serializable;
import java.time.Year;

public abstract class Guitar implements Serializable, Comparable<Guitar>, CsvConvertible {

    private Year year;
    private byte numberOfStrings;
    private String brand;
    private String model;
    private double scale;
    private String bodyWood;
    private String topWood;
    private String neckWood;
    private String fingerboardWood;
    private byte numberOfFrets;

    public Guitar(Year year, byte numberOfStrings, String brand, String model, double scale,
                  String bodyWood, String topWood, String neckWood, String fingerboardWood,
                  byte numberOfFrets) {
        this.year = year;
        this.numberOfStrings = numberOfStrings;
        this.brand = brand;
        this.model = model;
        this.scale = scale;
        this.bodyWood = bodyWood;
        this.topWood = topWood;
        this.neckWood = neckWood;
        this.fingerboardWood = fingerboardWood;
        this.numberOfFrets = numberOfFrets;
    }

    @Override
    public String toString() {
        return  "Year: " + year + "\n" +
                "Number of strings: " + numberOfStrings + "\n" +
                "Brand: " + brand + "\n" +
                "Model: " + model + "\n" +
                "Scale: " + scale + "\n" +
                "Body Wood: " + bodyWood + "\n" +
                "Top Wood: " + topWood + "\n" +
                "Neck Wood: " + neckWood + "\n" +
                "Fingerboard Wood: " + fingerboardWood + "\n" +
                "Number of Frets: " + numberOfFrets;
    }

    @Override
    public int compareTo(Guitar guitar) {
        return brand.compareToIgnoreCase(guitar.brand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guitar guitar = (Guitar) o;

        if (numberOfStrings != guitar.numberOfStrings) return false;
        if (Double.compare(guitar.scale, scale) != 0) return false;
        if (numberOfFrets != guitar.numberOfFrets) return false;
        if (year != null ? !year.equals(guitar.year) : guitar.year != null) return false;
        if (brand != null ? !brand.equals(guitar.brand) : guitar.brand != null) return false;
        if (model != null ? !model.equals(guitar.model) : guitar.model != null) return false;
        if (bodyWood != null ? !bodyWood.equals(guitar.bodyWood) : guitar.bodyWood != null) return false;
        if (topWood != null ? !topWood.equals(guitar.topWood) : guitar.topWood != null) return false;
        if (neckWood != null ? !neckWood.equals(guitar.neckWood) : guitar.neckWood != null) return false;
        return fingerboardWood != null ? fingerboardWood.equals(guitar.fingerboardWood) : guitar.fingerboardWood == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = year != null ? year.hashCode() : 0;
        result = 31 * result + (int) numberOfStrings;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        temp = Double.doubleToLongBits(scale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (bodyWood != null ? bodyWood.hashCode() : 0);
        result = 31 * result + (topWood != null ? topWood.hashCode() : 0);
        result = 31 * result + (neckWood != null ? neckWood.hashCode() : 0);
        result = 31 * result + (fingerboardWood != null ? fingerboardWood.hashCode() : 0);
        result = 31 * result + (int) numberOfFrets;
        return result;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public byte getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(byte numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getBodyWood() {
        return bodyWood;
    }

    public void setBodyWood(String bodyWood) {
        this.bodyWood = bodyWood;
    }

    public String getTopWood() {
        return topWood;
    }

    public void setTopWood(String topWood) {
        this.topWood = topWood;
    }

    public String getNeckWood() {
        return neckWood;
    }

    public void setNeckWood(String neckWood) {
        this.neckWood = neckWood;
    }

    public String getFingerboardWood() {
        return fingerboardWood;
    }

    public void setFingerboardWood(String fingerboardWood) {
        this.fingerboardWood = fingerboardWood;
    }

    public byte getNumberOfFrets() {
        return numberOfFrets;
    }

    public void setNumberOfFrets(byte numberOfFrets) {
        this.numberOfFrets = numberOfFrets;
    }
}
