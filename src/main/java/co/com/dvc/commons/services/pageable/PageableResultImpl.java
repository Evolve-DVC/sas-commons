package co.com.dvc.commons.services.pageable;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageableResultImpl<M> implements IPageableResult<M> {

    private final List<M> content;
    private final int pageNumber;
    private final int pageSize;
    private final Long totalElements;

    public PageableResultImpl(Page<M> page) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
    }

    public PageableResultImpl(List<M> content, int pageNumber, int pageSize, Long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    @Override
    public List<M> getContent() {
        return content;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public Long getTotalElements() {
        return totalElements;
    }

    @Override
    public int getTotalPages() {
        if (pageSize <= 0 || totalElements == null || totalElements == 0L) {
            return 0;
        }
        return (int) Math.ceil((double) totalElements / pageSize);
    }
}
