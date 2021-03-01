/**
 * Admin class implements Employee interface. It has 2 methods. Just a single
 * object of this type will be created with "start" command.
 */
public class Admin implements Employee {

    /**
     * Adds a product in Auction House.
     *
     * @param product Product to be added.
     */
    public synchronized void addProduct(Product product) {
        AuctionHouse.getInstance().getProducts().add(product);
    }

    /**
     * Move a product from available to "in auction" state.
     *
     * @param product Product to be moved.
     */
    public synchronized void moveProductToAuction(Product product) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.getProducts().remove(product);
        auctionHouse.getProductsInAuctions().add(product);
    }
}
