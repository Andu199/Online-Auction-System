/**
 * Furniture class. It extends Product and has two more fields: the type of
 * furniture and the material.
 */
public class Furniture extends Product {
    private String type;
    private String material;

    public Furniture() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Product (Furniture " + getType() + ") " + getName() +
                " (id: " + getId() + ") from year " + getYear() + " made of "
                + getMaterial() + ". Last selling price: " + getSellingPrice() +
                ", Minimum sell price: " + getMinimumPrice();
    }
}
