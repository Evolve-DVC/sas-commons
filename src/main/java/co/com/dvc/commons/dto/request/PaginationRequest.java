package co.com.dvc.commons.dto.request;

public record PaginationRequest(
        Integer pageNumber,
        Integer pageSize,
        String sortBy,
        String sortDir,
        String filterType
) {
}
