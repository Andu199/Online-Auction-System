/**
 * Facade Design Pattern. It is used to wrap all project's functionalities.
 * Every command which is read in main has a method in Facade. In this way,
 * the project can be used by someone else just by using this simple methods.
 * The same goes if an API is used.
 */
public class Facade {
    /**
     * Starts the program. Initialize the Auction House, an Admin and
     * "brokersNo" brokers.
     *
     * @param brokersNo Initial number of brokers.
     */
    public void start(int brokersNo) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.setAdmin(new Admin());
        for(int i = 0; i < brokersNo; ++i)
            auctionHouse.getBrokers().add(new Broker());
        System.out.println("\nAuction House has started! (1 Admin and " +
                brokersNo + " Brokers)");
    }

    /**
     * Adds a new broker.
     */
    public void addBroker() {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.getBrokers().add(new Broker());
        System.out.println("\nNew Broker has been added!");
    }

    /**
     * Creates and adds a new Client.
     *
     * @param input Input string.
     */
    public void addClient(String input) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        Client client = ClientFactory.getClient(input);
        auctionHouse.getClients().add(client);
        System.out.println("\nClient " + client + " has been added!");
    }

    /**
     * Creates and adds a new Product.
     *
     * @param input Input string.
     */
    public void addProduct(String input) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        String[] list = input.split(",");
        ProductBuilder productBuilder = new ProductBuilder(list[1]);
        Product product = productBuilder.withID(Integer.parseInt(list[2]))
                .withName(list[3])
                .withSellingPrice(Double.parseDouble(list[4]))
                .withMinimumPrice(Double.parseDouble(list[5]))
                .withYear(Integer.parseInt(list[6]))
                .withFirstArgument(list[7])
                .withSecondArgument(list[8])
                .build();
        auctionHouse.getAdmin().addProduct(product);
        System.out.println("\nProduct " + product + " has been added!");
    }

    /**
     * Auxiliary method. Finds a client by its ID.
     *
     * @param id Client's ID.
     * @return The client.
     */
    private Client findClientByID(int id) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        for(Client client : auctionHouse.getClients())
            if(client.getId() == id)
                return client;
        throw new ClientNotFoundException();
    }

    /**
     * Finds the client which fits the input. This client will notify the
     * Auction House about its request.
     *
     * @param input Input string.
     */
    public void bidForProduct(String input) {
        String[] list = input.split(",");
        Client client = findClientByID(Integer.parseInt(list[2]));
        client.notifyActionHouse(Integer.parseInt(list[4]),
                Double.parseDouble(list[6]));
        System.out.println("\nClient " + client + " is interested in product" +
                " with id " + Integer.parseInt(list[4]));
    }

    /**
     * Prints all brokers using the generic method "printActive()".
     */
    public synchronized void printBrokers() {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        System.out.println("\nList with all brokers and their profit:\n");
        auctionHouse.printActive(auctionHouse.getBrokers());
    }

    /**
     * Prints all clients using the generic method "printActive()".
     */
    public synchronized void printClients() {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        System.out.println("\nList with all clients:\n");
        auctionHouse.printActive(auctionHouse.getClients());
    }

    /**
     * Prints all products using the generic method "printActive()".
     */
    public synchronized void printProducts() {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        System.out.println("\nList with all products:\n");
        auctionHouse.printActive(auctionHouse.getProducts());
    }
}
