import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader=new CSVFileReader();
        List<Transaction> transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        FeeCalculator calculator=new FeeCalculator();
        calculator.calculateTransactionsFee(transactions);
        calculator.getSummaryReport(calculator.transactions);
    }
}
