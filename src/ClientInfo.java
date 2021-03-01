/**
 * Class used to store all important information about a client which wants to
 * participate in an auction (this information is useful for brokers).
 */
public class ClientInfo {
    private Client client;
    private double maximumPrice;
    private int auctionID;

    public ClientInfo(Client client, double maximumPrice, int auctionID) {
        this.client = client;
        this.maximumPrice = maximumPrice;
        this.auctionID = auctionID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
}
