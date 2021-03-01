/**
 * Jewel class. It extends Product and has two more fields: the material and a
 * boolean (true if it contains gemstones and false otherwise).
 */
public class Jewel extends Product {
    private String material;
    private boolean gemstone;

    public Jewel() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isGemstone() {
        return gemstone;
    }

    public void setGemstone(boolean gemstone) {
        this.gemstone = gemstone;
    }

    @Override
    public String toString() {
        if(isGemstone())
            return "Product (Jewel with gemstone) " + getName() +
                " (id: " + getId() + ") from year " + getYear() +
                " made of " + getMaterial() +
                ". Last selling price: " + getSellingPrice() +
                ", Minimum sell price: " + getMinimumPrice();
        return "Product (Jewel) " + getName() +
                " (id: " + getId() + ") from year " + getYear() +
                " made of " + getMaterial() +
                ". Last selling price: " + getSellingPrice() +
                ", Minimum sell price: " + getMinimumPrice();
    }
}
