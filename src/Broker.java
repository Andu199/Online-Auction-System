import java.util.ArrayList;

/**
 * Broker class contains a list of ClientInfo objects for all clients that are
 * associated to this broker and also his profit.
 */
public class Broker implements Employee {
    private final ArrayList<ClientInfo> clients = new ArrayList<>();
    private double profit = 0;

    public ArrayList<ClientInfo> getClients() {
        return clients;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    /**
     * Brokers are able to remove a product. Products are removed from the list
     * of products in auctions (after the product is sold to a client).
     *
     * @param product Product to be removed.
     */
    public synchronized void deleteProduct(Product product) {
        AuctionHouse.getInstance().getProductsInAuctions().remove(product);
    }

    /**
     * Method used to find the client (ClientInfo object) in the list of clients
     * which wants to participate in the auction with the ID "auctionID".
     *
     * @param auctionID The ID of a specific auction.
     * @return A ClientInfo object.
     * @throws ClientNotFoundException thrown if client is not found.
     */
    private synchronized ClientInfo findClient(int auctionID)
            throws ClientNotFoundException {
        for(ClientInfo clientInfo : clients)
            if(clientInfo.getAuctionID() == auctionID)
                return clientInfo;
        throw new ClientNotFoundException();
    }

    /**
     * Method which is used to apply commission by broker.
     *
     * @param price The price to apply commission on.
     * @param auctionID The ID of an auction.
     * @throws ClientNotFoundException thrown if client is not found.
     */
    public synchronized void applyCommission(double price, int auctionID)
            throws ClientNotFoundException {
        ClientInfo client = findClient(auctionID);
        if(client.getClient() instanceof NaturalPerson)
            if(client.getClient().getAttendanceNo() < 5)
                profit += price * 0.2;
            else
                profit += price * 0.15;
        else
            if(client.getClient().getAttendanceNo() < 25)
                profit += price * 0.25;
            else
                profit += price * 0.1;
    }

    /**
     * Broker asks his client for the new offer.
     *
     * @param client Client which is asked for the offer.
     * @param maximumPrice The maximum price to be offered by the client.
     * @param currentOffer The current offer for a specific product.
     * @return New offer.
     */
    public synchronized double askForBid(Client client, double maximumPrice,
                                         double currentOffer) {
        return client.bid(maximumPrice, currentOffer);
    }

    /**
     * Broker notifies his client which participated in the auction with the ID
     * "auctionID" that they won.
     *
     * @param auctionID The ID of an auction.
     */
    public synchronized void notifyClient(int auctionID) {
        for(ClientInfo clientInfo : clients)
            if(clientInfo.getAuctionID() == auctionID)
                clientInfo.getClient()
                        .setAuctionsWon(clientInfo.getClient().getAuctionsWon() + 1);
    }

    @Override
    public String toString() {
        return "Broker with profit " + profit;
    }
}
