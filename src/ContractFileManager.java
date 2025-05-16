import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class ContractFileManager {

    public ArrayList<BusinessContract> load(){

    ArrayList<BusinessContract> list = new ArrayList<>();

    String line = ""; //Read this
    BusinessContract bc = parseContract(line);
    list.add(bc);

    return list;
    }

    public void save(ArrayList<BusinessContract> list){
        for(BusinessContract c : list){
            System.out.println("SAVING: " + c);
            String line = getContractString(c);
            System.out.println(line);
        }
    }

    BusinessContract parseContract(String line){
        int MIN_LENGTH = 16;
        int CONTRACT_TYPE = 0;
        int CONTRACT_DATE = 1;
        int CUSTOMER_NAME = 2;
        int CUSTOMER_EMAIL = 3;

        int VIN =4;
        int YEAR = 5;
        int MAKE = 6;
        int MODEL = 7;
        int TYPE = 8;
        int COLOR = 9;
        int MILES = 10;
        int PRICE = 11;

        //Begin Parsing
        String[] parts = line.split("\\|");

        if(parts.length < MIN_LENGTH){
            System.out.println("ERROR IN LINE: " + line);
            return null;
        }

        Vehicle vehicle = new Vehicle(
                Integer.parseInt(parts[VIN]),
                Integer.parseInt(parts[YEAR]),
                parts[MAKE],
                parts[MODEL],
                parts[TYPE],
                parts[COLOR],
                Integer.parseInt(parts[MILES]),
                Double.parseDouble(parts[PRICE])
        );
        String contractType = parts[CONTRACT_TYPE];
        System.out.println(contractType);

        Date date = new Date();
        try{
            LocalDate 1d =LocalDate.parse(parts[CONTRACT_DATE], DateTimeFormatter.ofPattern("yyyyMMdd"));
            date = java.util.Date.from(1d,atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e){
            System.out.println("Error parsing date: " + e.getMessage());
        }

        String customerName = parts[CUSTOMER_NAME];
        String customerEmail = parts[CUSTOMER_EMAIL];

        boolean isSold = true;

        if(contractType.equalsIgnoreCase("SALE")){
            double totalPrice = Double.parseDouble(parts[TOTAL_AMOUNT]);
        }

    }

}
