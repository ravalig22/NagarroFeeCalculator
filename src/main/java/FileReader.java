import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@FunctionalInterface
public interface FileReader {
    List<Transaction> read(File file);

    default Transaction readTransaction(String[] a) {
        Transaction transaction = Transaction.builder()
                .externalTransaction(a[0])
                .clientId(a[1])
                .securityId(a[2])
                .transactionType(parseToTransactionType(a[3]))
                .transactionDate(parseToTransactionDate(a[4]))
                .marketValue(parseToMarketValue(a[5]))
                .priorityFlag(parseToPriorityFlag(a[6]))
                .build();
        return transaction;
    }
    default String parseToTransactionType(String s) {
        switch (s.toUpperCase()) {
            case "BUY":
            case "SELL":
            case "DEPOSIT":
            case "WITHDRAW":
                return s;
            default:
                return null;
        }
    }

    default String parseToTransactionDate(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate dateTime = LocalDate.parse(s, formatter);
            return dateTime.format(formatter);
        } catch (Exception e) {
            return null;
        }

    }

    default double parseToMarketValue(String s) {
        try {
            double d = Double.valueOf(s);
            return d;
        } catch (Exception e) {
            return 0;
        }
    }

    default Boolean parseToPriorityFlag(String s) {
        String s1 = s.trim();
        switch (s1.toUpperCase()) {
            case "Y":
                return true;
            default:
                return false;
        }
    }

}
