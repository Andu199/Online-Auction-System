import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class which contains the static method main. All commands are read in
 * an infinite loop (just "exit" and "quit" commands can stop the program).
 * A facade object will be instantiated at the beginning. For every command
 * there exists a method in facade. After each command, Thread.sleep() is
 * called so that the sensation of a flow of commands is created.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Facade facade = new Facade();
        while(true) {
            String input = scanner.nextLine();
            String[] list = input.split(",");
            try {
                switch (list[0]) {
                    case "start" -> facade.start(Integer.parseInt(list[1]));
                    case "addbroker" -> facade.addBroker();
                    case "addclient" -> facade.addClient(input);
                    case "addproduct" -> facade.addProduct(input);
                    case "bidforproduct" -> facade.bidForProduct(input);
                    case "printbrokers" -> facade.printBrokers();
                    case "printclients" -> facade.printClients();
                    case "printproducts" -> facade.printProducts();
                    case "quit", "exit" -> { return; }
                    default -> throw new CommandNotFoundException();
                }
                Thread.sleep(1000);
            }
            catch (ProductNotFoundException e) {
                System.out.println();
                System.out.println("PRODUCT NOT FOUND\n");
            }
            catch (RuntimeException | InterruptedException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
