/**
 * Painting class. It extends Product and has two more fields: the name of the
 * artist and the color type (enum).
 */
public class Painting extends Product {
    private String artistName;
    private ColorType color;

    public Painting() {
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product (Painting " + getColor() + ") " + getName() +
                " (id: " + getId() + ") from year " + getYear() + " by "
                + getArtistName() + ". Last selling price: " + getSellingPrice()
                + ", Minimum sell price: " + getMinimumPrice();
    }
}
