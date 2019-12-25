import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FeeCalculatorTest {

    private List<Transaction> transactions;
    private FileReader fileReader = new CSVFileReader();
    private FeeCalculator feeCalculator = new FeeCalculator();

    @Test
    public void intraDayTransaction_FeeCalculation() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        feeCalculator.calculateTransactionsFee(transactions);
        assertNotNull(transactions);
        assertEquals(transactions.get(1).getProcessingFee(), Double.valueOf(10.00));
    }

    @Test
    public void normalTransactionWithPriority_FeeCalculation() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        feeCalculator.calculateTransactionsFee(transactions);
        assertNotNull(transactions);
        assertEquals(transactions.get(3).getProcessingFee(), Double.valueOf(500.00));
    }

    @Test
    public void normalTransactionWithNormalPrioritySellAndWithdraw_FeeCalculation() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        feeCalculator.calculateTransactionsFee(transactions);
        assertNotNull(transactions);
        assertEquals(transactions.get(2).getProcessingFee(), Double.valueOf(50.00));
    }

    @Test
    public void normalTransactionWithNormalPriorityBuyAndDeposit_FeeCalculation() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        feeCalculator.calculateTransactionsFee(transactions);
        assertNotNull(transactions);
        assertEquals(transactions.get(4).getProcessingFee(), Double.valueOf(100.00));
    }


}

