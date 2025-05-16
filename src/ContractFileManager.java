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

        //Vehicle
        int VIN =4;
        int YEAR = 5;
        int MAKE = 6;
        int MODEL = 7;
        int TYPE = 8;
        int COLOR = 9;
        int MILES = 10;
        int PRICE = 11;

        //Lease Variation
        int EXPECTED_ENDING_VALUE = 12;
        int LEASE_FEE = 13;
        int COMBINED = 14;
        int MONTHLY_LEASE = 15;

        //Sales Variation

        int SALES_TAX_AMOUNT = 12;
        int RECORDING_FEE = 13;
        int PROCESSING_FEE = 14;
        int TOTAL_AMOUNT = 15;
        int IS_FINANCED = 16;
        int MONTHLY_PAYMENT = 17;
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
            double salesTaxAmount = Double.parseDouble(parts[SALES_TAX_AMOUNT]);
            double recordingFee = Double.parseDouble(parts[RECORDING_FEE]);
            double processingFee = Double.parseDouble(parts[PROCESSING_FEE]);
            boolean isFinanced = parts[IS_FINANCED].equalsIgnoreCase("YES");

            return new SalesContract(vehicle, date, customerName, customerEmail, isSold, totalPrice, salesTaxAmount, recordingFee, processingFee, isFinanced);
        }else if(contractType.equalsIgnoreCase("Lease")){
            double expectedValue = Double.parseDouble(parts[EXPECTED_ENDING_VALUE]);
            double fee = Double.parseDouble(parts[LEASE_FEE]);
            double combined = Double.parseDouble(parts[COMBINED]);
            double monthly = Double.parseDouble(parts[MONTHLY_LEASE]);

            return new LeaseContract(vehicle, date, customerName, customerEmail, isSold, combined, expectedValue, fee);
        }
        return null;
    }

    String getContractString(BusinessContract bc){
        return "";
    }

    public static void main(String[] args){
        ContractFileManager cfm = new ContractFileManager();

        BusinessContract bc1 = cfm.parseContract("SALE|20210928|Dana Wyatt|dana@texas.com|10112|1995|Ford|Explorer|SUV|Red|525123|9595.00|49.75|100.00|295.00|1439.75|No|0.00");
        System.out.println(bc1);

        BusinessContract bc2 = cfm.parseContract("")
    }

}
