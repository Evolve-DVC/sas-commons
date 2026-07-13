package co.com.dvc.commons.dto.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PageContext<T> {

    private T data;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String sortDir;
    private String filterType;
}
