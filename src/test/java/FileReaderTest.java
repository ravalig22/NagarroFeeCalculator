import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileReaderTest {
    private List<Transaction> transactions;
    private FileReader fileReader = new CSVFileReader();

    @Test
    public void givenCSVfile_readTransactionsFromFile() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        assertNotNull(transactions);
        assertEquals(transactions.size(), 6);
    }

    @Test
    public void givenCSVfile_getTransactionFromFile() {
        transactions = fileReader.read(new File("src/main/resources/sample.csv"));
        assertNotNull(transactions);
        assertEquals(transactions.get(0).getClientId(), "CLIENTID1");
        assertEquals(transactions.get(0).getSecurityId(), "RELIND");
        assertEquals(transactions.get(0).getTransactionDate(), "12/22/2019");
        assertEquals(transactions.get(0).getTransactionType(), "BUY");

    }

}
