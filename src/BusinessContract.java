import java.util.Date;

public class BusinessContract {
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

}
