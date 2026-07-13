package co.com.dvc.commons.services.pageable;

import java.util.List;

public interface IPageableResult<T> {

    List<T> getContent();

    int getPageNumber();

    int getPageSize();

    Long getTotalElements();

    int getTotalPages();
}
