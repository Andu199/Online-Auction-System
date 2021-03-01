/**
 * A class which represents a juridical person. This type of client also
 * possess a social capital and a CompanyType (SRL or SA).
 */
public class JuridicalPerson extends Client {
    private CompanyType company;
    private double socialCapital;

    public JuridicalPerson() {
        super();
        this.socialCapital = 0;
    }

    public JuridicalPerson(int id, String name, String address,
                           CompanyType company, double socialCapital) {
        super(id, name, address);
        this.company = company;
        this.socialCapital = socialCapital;
    }

    public CompanyType getCompany() {
        return company;
    }

    public void setCompany(CompanyType company) {
        this.company = company;
    }

    public double getSocialCapital() {
        return socialCapital;
    }

    public void setSocialCapital(double socialCapital) {
        this.socialCapital = socialCapital;
    }

    @Override
    public String toString() {
        return "Juridical Person " + getName() + " " + getCompany()
                + " (id: " + getId() + "), with address: " + getAddress() +
                ". Attendances: " + getAttendanceNo() + ", Auctions won: " +
                getAuctionsWon() + ", Social capital: " + getSocialCapital();
    }
}
