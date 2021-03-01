import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Auction House class is implemented as with Singleton Design Pattern.
 * It contains lists for: available products, products in auctions, clients,
 * auctions and brokers. It also stores the Admin object.
 */
public class AuctionHouse {
    private static AuctionHouse instance;
    private final ArrayList<Product> products;
    private final ArrayList<Product> productsInAuctions;
    private final ArrayList<Client> clients;
    private final ArrayList<Auction> auctions;
    private final ArrayList<Broker> brokers;
    private Admin admin;

    private AuctionHouse() {
        products = new ArrayList<>();
        productsInAuctions = new ArrayList<>();
        clients = new ArrayList<>();
        auctions = new ArrayList<>();
        brokers = new ArrayList<>();
    }

    public static AuctionHouse getInstance() {
        if(instance == null)
            instance = new AuctionHouse();
        return instance;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getProductsInAuctions() {
        return productsInAuctions;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public ArrayList<Broker> getBrokers() {
        return brokers;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Update an auction. This method is called when a client sign up for an
     * auction.
     *
     * @param auction Auction to be updated.
     */
    private void updateAuctions(Auction auction) {
        auction.setCurrentParticipants(auction.getCurrentParticipants() + 1);
        if(auction.getCurrentParticipants() == auction.getParticipantsNo()) {
            auctions.remove(auction);
            Product product = findProduct(auction.getProductID());
            AuctionHouse.getInstance().getAdmin().moveProductToAuction(product);
            new Thread(auction).start();
        }
    }

    /**
     * Finds a product by its ID.
     *
     * @param productID Product ID.
     * @return Product to be found.
     */
    private Product findProduct(int productID) {
        for(Product product : products) {
            if(product.getId() == productID)
                return product;
        }
        return null;
    }

    /**
     * Associate a client with an eligible broker and sign him up for the
     * auction. If there is no auction for this specific product, a new one is
     * created.
     *
     * @param client Information about a client who sign up for a product.
     * @throws NoEligibleBroker Exception thrown because there is no broker
     *                          eligible for the client.
     * @throws ProductNotFoundException Exception thrown because product
     * cannot be found.
     */
    public void associate(ClientInfo client)
            throws NoEligibleBroker, ProductNotFoundException {
        Auction current = null;
        if(findProduct(client.getAuctionID()) == null)
            throw new ProductNotFoundException();
        for(Auction auction : auctions)
            if(auction.getId() == client.getAuctionID()) {
                current = auction;
                break;
            }
        if(current == null) {
            current = new Auction(client.getAuctionID(), client.getAuctionID(),
                    (int) (2 + Math.random() * 4), (int) (2 + Math.random() * 3));
            auctions.add(current);
        }

        ArrayList<Broker> notOccupied = new ArrayList<>();
        for(Broker broker : brokers) {
            boolean eligible = true;
            for (ClientInfo auxClient : broker.getClients())
                if (auxClient.getAuctionID() == current.getId()) {
                    eligible = false;
                    break;
                }
            if(eligible)
                notOccupied.add(broker);
        }
        if(notOccupied.size() == 0)
            throw new NoEligibleBroker();
        notOccupied.get((int) (Math.random() * notOccupied.size())).getClients().add(client);
        updateAuctions(current);
    }

    /**
     * Calculates the maximum offer and returns it and its broker.
     *
     * @param priceMap A map with brokers as key and how much their clients
     *                 offered as value.
     * @return A Pair object (Broker, Double).
     */
    public synchronized Pair<Broker, Double> calculateMax(Map<Broker, Double> priceMap) {
        Pair<Broker, Double> result = new Pair<>(null, -1d);
        for (Map.Entry<Broker, Double> entry : priceMap.entrySet()) {
            if(entry.getValue() > result.getSecond()) {
                result.setFirst(entry.getKey());
                result.setSecond(entry.getValue());
            }
        }
        return result;
    }

    /**
     * Prints a list of generic type.
     */
    public <T> void printActive(List<T> list) {
        list.forEach((element) -> System.out.println(element.toString()));
    }
}
