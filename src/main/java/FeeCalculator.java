import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FeeCalculator {
    List<Transaction> transactions = new ArrayList<>();

    public void calculateTransactionsFee(List<Transaction> list) {
        for (Transaction t : list) {
            Transaction txn = calculateFee(t);
            transactions.add(txn);
        }
    }

    public void getSummaryReport(List<Transaction> transactionList) {
        Comparator<Transaction> transactionComparator = Comparator.comparing(Transaction::getClientId)
                .thenComparing(Transaction::getTransactionType)
                .thenComparing(Transaction::getTransactionDate)
                .thenComparing(Transaction::getPriorityFlag);
        transactionList.sort(transactionComparator);
        System.out.println(String.format("%-10s%-15s%-20s%-10s%-10s", "Client Id", "Transaction", "Transaction Date", "Priority", "Processing Fee"));
        for (Transaction t : transactionList) {
            System.out.println(String.format("%-10s%-15s%-20s%-10s%-10s", t.getClientId(), t.getTransactionType(), t.getTransactionDate(), t.getPriorityFlag(), t.getProcessingFee()));
        }

    }

    private Transaction calculateFee(Transaction transaction) {
        if (intraDayTransaction(transaction)) {
            transaction.setProcessingFee(10.00);
        } else {
            if (transaction.getPriorityFlag().equals(true)) {
                transaction.setProcessingFee(500.00);
            } else {
                if ((transaction.getTransactionType().contentEquals(Constants.BUY)) || (transaction.getTransactionType().contentEquals(Constants.DEPOSIT))) {
                    transaction.setProcessingFee(50.00);
                } else if (transaction.getTransactionType().contentEquals(Constants.SELL) || transaction.getTransactionType().contentEquals(Constants.WITHDRAW)) {
                    transaction.setProcessingFee(100.00);
                }
            }
        }
        return transaction;
    }

    private Boolean intraDayTransaction(Transaction transaction) {
        boolean isIntradayTransaction = false;
        for (Transaction t : transactions) {
            if (transaction.getClientId().contentEquals(t.getClientId())
                    && transaction.getSecurityId().contentEquals(t.getSecurityId())
                    && transaction.getTransactionDate().contentEquals(t.getTransactionDate())) {
                if ((transaction.getTransactionType().contentEquals(Constants.BUY) && t.getTransactionType().contentEquals(Constants.SELL))
                        || (t.getTransactionType().contentEquals(Constants.BUY) && transaction.getTransactionType().contentEquals(Constants.SELL))) {
                    t.setProcessingFee(10.00);
                    isIntradayTransaction = true;
                    break;
                }
            }
        }
        return isIntradayTransaction;
    }


}
