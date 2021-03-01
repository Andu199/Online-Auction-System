/**
 * A class which represents a natural person. This type of client also possess
 * a birthday date.
 */
public class NaturalPerson extends Client {
    private String birthdayDate;

    public NaturalPerson() {
        super();
        this.birthdayDate = "None";
    }

    public NaturalPerson(int id, String name, String address, String birthdayDate) {
        super(id, name, address);
        this.birthdayDate = birthdayDate;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public String toString() {
        return "Natural Person " + getName() + " (id: " + getId() +
                "), with address: " + getAddress() + ", birthday date: " +
                getBirthdayDate() + ". Attendances: " + getAttendanceNo() +
                ", Auctions won: " + getAuctionsWon();
    }
}
