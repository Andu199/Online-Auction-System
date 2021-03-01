/**
 * Factory class with static Factory Method Design Pattern.
 */
public class ClientFactory {
    /**
     * Gets an input, parses it, creates a Juridical or a Natural Client and
     * returns it.
     *
     * @param input A string with all information.
     * @return A client
     * @throws ClientNotFoundException thrown if the input is wrong.
     */
    public static Client getClient(String input)
            throws ClientNotFoundException {
        String[] list = input.split(",");
        return switch (list[1]) {
            case "Natural" -> new NaturalPerson(Integer.parseInt(list[2]),
                    list[3], list[4], list[5]);
            case "Juridical" -> new JuridicalPerson(Integer.parseInt(list[2]),
                    list[3], list[4], CompanyType.valueOf(list[5]),
                    Double.parseDouble(list[6]));
            default -> throw new ClientNotFoundException();
        };
    }
}
