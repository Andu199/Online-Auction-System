import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Auction class shapes the behaviour of an auction. It implements Runnable.
 * An object has an ID, a product ID, number of participants to start the actual
 * auction, current number of participants and maximum steps.
 */
public class Auction implements Runnable {
    private int id;
    private int participantsNo;
    private int currentParticipants;
    private int productID;
    private int maximumStepsNo;

    public Auction(int id, int productID, int participantsNo, int maximumStepsNo) {
        this.id = id;
        this.currentParticipants = 0;
        this.productID = productID;
        this.participantsNo = participantsNo;
        this.maximumStepsNo = maximumStepsNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipantsNo() {
        return participantsNo;
    }

    public void setParticipantsNo(int participantsNo) {
        this.participantsNo = participantsNo;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getMaximumStepsNo() {
        return maximumStepsNo;
    }

    public void setMaximumStepsNo(int maximumStepsNo) {
        this.maximumStepsNo = maximumStepsNo;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    /**
     * Map for this specific auction all brokers with their clients.
     *
     * @param brokers List with all brokers from Auction House.
     * @return A map with brokers as key and Client Information as value.
     */
    private synchronized Map<Broker, ClientInfo> prepareBrokers(ArrayList<Broker> brokers) {
        Map<Broker, ClientInfo> map = new HashMap<>();
        for(Broker broker : brokers) {
            ClientInfo current = null;
            for (ClientInfo client : broker.getClients())
                if (client.getAuctionID() == id)
                    current = client;
            map.put(broker, current);
        }
        return map;
    }

    /**
     * Finds the product for this auction.
     *
     * @param products List with all products.
     * @return The specific product for this auction.
     * @throws ProductNotFoundException Exception thrown because the product
     * cannot be found.
     */
    private synchronized Product findProduct(ArrayList<Product> products)
            throws ProductNotFoundException {
        for(Product product : products)
            if(product.getId() == productID)
                return product;
        throw new ProductNotFoundException();
    }

    /**
     * An auxiliary method used at the end of "run" method. Verify if the
     * minimum price of product is greater than the final offer. If so,
     * the product is added back in the list of available products (product
     * has not been sold). Otherwise, the product is removed, commission is
     * applied and the winner broker notifies its client that they won.
     *
     * @param winner The winner broker of this auction.
     * @param offer The final offer for this product.
     */
    private synchronized void endOfAuction(Broker winner, double offer) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        if(winner == null)
            return;
        try {
            Product product = findProduct(auctionHouse.getProductsInAuctions());
            if(product.getMinimumPrice() > offer) {
                System.out.println("\nProduct with id " + product.getId() +
                        " has not been sold");
                auctionHouse.getAdmin().addProduct(product);
                return;
            }
            System.out.println("\nProduct with id " + product.getId() +
                    " has been sold for " + offer + "EUR");
            winner.deleteProduct(product);
            winner.applyCommission(offer, id);
            winner.notifyClient(id);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update for all clients which have been represented in this auction the
     * number of attendances.
     *
     * @param map A map with brokers as keys an their client's info as value.
     */
    private synchronized void updateAttendances(Map<Broker, ClientInfo> map) {
        for (Map.Entry<Broker, ClientInfo> entry : map.entrySet()) {
            if(entry.getValue() != null)
                entry.getValue().getClient().setAttendanceNo(
                    entry.getValue().getClient().getAttendanceNo() + 1
            );
        }
    }

    /**
     * Implemented method from Runnable interface. It simulates an auction.
     * There are "maximumStepsNo" steps in this auction and, for every step,
     * all clients bid via their broker. The Auction House computes the best
     * offer and returns a Pair object (first element - winner broker, second
     * element - new offer). Thread.sleep is used at the end to simulate the
     * behaviour of an auction.
     */
    @Override
    public synchronized void run() {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        Map<Broker, ClientInfo> map = prepareBrokers(auctionHouse.getBrokers());
        double currentOffer = 0, newOffer;
        Broker winner = null;
        for(int i = 0; i < maximumStepsNo; ++i) {
            Map<Broker, Double> priceMap = new HashMap<>();
            for (Map.Entry<Broker, ClientInfo> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    priceMap.put(entry.getKey(), 0d);
                    continue;
                }
                newOffer = entry.getKey().askForBid(entry.getValue().getClient(),
                        entry.getValue().getMaximumPrice(), currentOffer);
                priceMap.put(entry.getKey(), newOffer);
            }
            Pair<Broker,Double> result = auctionHouse.calculateMax(priceMap);
            winner = result.getFirst();
            currentOffer = result.getSecond();
            System.out.println();
            System.out.println("In auction " + id + ", in the round " + (i + 1) +
                    ", the winner is " + map.get(winner).getClient().getName() +
                    " (current offer " + currentOffer + "EUR)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endOfAuction(winner, currentOffer);
        updateAttendances(map);
    }
}
