/**
 * Builder Design Pattern for Product class and its subclasses.
 * The constructor for ProductBuilder takes a String which represents the type
 * and initialize the private final product. In the last two methods
 * (withFirstArgument and withSecondArgument) instanceof is used to determine
 * what type is the object.
 */
public class ProductBuilder {
    private final Product product;

    public ProductBuilder(String name) {
        product = switch (name) {
            case "Painting" -> new Painting();
            case "Furniture" -> new Furniture();
            case "Jewel" -> new Jewel();
            default -> throw new InvalidClassException();
        };
    }

    public ProductBuilder withID(int id) {
        product.setId(id);
        return this;
    }

    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder withSellingPrice(double sellingPrice) {
        product.setSellingPrice(sellingPrice);
        return this;
    }

    public ProductBuilder withMinimumPrice(double minimumPrice) {
        product.setMinimumPrice(minimumPrice);
        return this;
    }

    public ProductBuilder withYear(int year) {
        product.setYear(year);
        return this;
    }

    public ProductBuilder withFirstArgument(String input)
        throws ProductNotFoundException {
        if(product instanceof Painting)
            ((Painting) product).setArtistName(input);
        else if(product instanceof Furniture)
            ((Furniture) product).setType(input);
        else if(product instanceof Jewel)
            ((Jewel) product).setMaterial(input);
        else
            throw new ProductNotFoundException();
        return this;
    }

    public ProductBuilder withSecondArgument(String input)
            throws ProductNotFoundException {
        if(product instanceof Painting)
            ((Painting) product).setColor(ColorType.valueOf(input));
        else if(product instanceof Furniture)
            ((Furniture) product).setMaterial(input);
        else if(product instanceof Jewel)
            ((Jewel) product).setGemstone(Boolean.parseBoolean(input));
        else
            throw new ProductNotFoundException();
        return this;
    }

    public Product build() {
        return product;
    }
}
