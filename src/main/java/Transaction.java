import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Transaction {
    private String externalTransaction;
    private String clientId;
    private String securityId;
    private String transactionType;
    private String transactionDate;
    private Double marketValue;
    private Boolean priorityFlag;
    private Double processingFee;
}
