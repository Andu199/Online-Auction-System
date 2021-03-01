/**
 * Each client has an unique ID, a name, an address, number of attendances
 * and number of auctions won.
 */
public abstract class Client {
    private int id;
    private String name;
    private String address;
    private int attendanceNo;
    private int auctionsWon;

    public Client() {
        this.id = -1;
        this.address = "None";
        this.name = "None";
        this.attendanceNo = 0;
        this.auctionsWon = 0;
    }

    public Client(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.attendanceNo = 0;
        this.auctionsWon = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAttendanceNo() {
        return attendanceNo;
    }

    public void setAttendanceNo(int attendanceNo) {
        this.attendanceNo = attendanceNo;
    }

    public int getAuctionsWon() {
        return auctionsWon;
    }

    public void setAuctionsWon(int auctionsWon) {
        this.auctionsWon = auctionsWon;
    }

    /**
     * Randomize a new offer based on the maximum offer and the current one.
     *
     * @param maximumPrice Maximum price which this client can offer.
     * @param currentOffer Current offer in the auction.
     * @return The new offer.
     */
    public synchronized double bid(double maximumPrice, double currentOffer) {
        if(currentOffer > maximumPrice)
            return 0;
        return currentOffer + Math.random() * (maximumPrice - currentOffer);
    }

    /**
     * Notifies the Auction House about what product this client wants to bid
     * for and what maximum price he can offer. Firstly, it is created a
     * ClientInfo object through which the association is made.
     *
     * @param productID ID of a product.
     * @param maxPrice Maximum price which this client can offer.
     */
    public void notifyActionHouse(int productID, double maxPrice) {
        ClientInfo clientInfo = new ClientInfo(this, maxPrice, productID);
        AuctionHouse.getInstance().associate(clientInfo);
    }
}
