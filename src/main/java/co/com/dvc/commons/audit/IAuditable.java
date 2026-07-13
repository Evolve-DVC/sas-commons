package co.com.dvc.commons.audit;

import java.time.Instant;

public interface IAuditable {
    String getCreatedBy();
    Instant getCreatedAt();
    String getUpdatedBy();
    Instant getUpdatedAt();
}
