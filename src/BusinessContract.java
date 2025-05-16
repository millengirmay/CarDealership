import java.util.Date;

public abstract class BusinessContract {
    private Date date;
    private String customerName;
    private String customerEmail;
    private boolean isSold;
    private double totalPrice;
    private double monthlyPayment;
    private Vehicle vehicle;

    //Constructor
    BusinessContract(Date date, String customerName, String customerEmail,
                     boolean isSold, double totalPrice, Double monthlyPayment, Vehicle vehicle){
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerName;
        this.isSold = isSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.vehicle = vehicle;
    }

    public Date getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isSold() {
        return isSold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return String.format("""
                Date: %s
                Name: %s
                Email: %s
                Sold: %s
                Total: %.2f
                Monthly: %.2f""",
                this.date,
                this,customerName,
                this,customerEmail,
                this.isSold ? "YES" : "NO",
                this.totalPrice,
                this.monthlyPayment);

    }
}
