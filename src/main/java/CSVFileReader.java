import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader implements FileReader {
    public List<Transaction> read(File csvFile) {
        String line = "";
        String split = ",";
        BufferedReader reader = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            reader = new BufferedReader(new java.io.FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                String[] transactionArray = line.split(split);
                Transaction transaction = readTransaction(transactionArray);
                transactions.add(transaction);
            }
            return transactions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
