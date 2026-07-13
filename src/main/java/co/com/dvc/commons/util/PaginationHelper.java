package co.com.dvc.commons.util;

import co.com.dvc.commons.services.pageable.IPageableResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.function.Function;

public class PaginationHelper {

    private PaginationHelper() {}

    public static <M, R> Page<R> mapPage(IPageableResult<M> result, Function<M, R> mapper) {
        if (result == null) {
            return Page.empty();
        }

        List<R> mappedContent = result.getContent().stream()
                .map(mapper)
                .toList();

        PageRequest pageRequest = PageRequest.of(result.getPageNumber(), result.getPageSize());
        return new PageImpl<>(mappedContent, pageRequest, result.getTotalElements());
    }

    public static <M, R> Page<R> mapPageList(IPageableResult<M> result, Function<List<M>, List<R>> listMapper) {
        if (result == null) {
            return Page.empty();
        }

        List<R> mappedContent = listMapper.apply(result.getContent());
        PageRequest pageRequest = PageRequest.of(result.getPageNumber(), result.getPageSize());
        return new PageImpl<>(mappedContent, pageRequest, result.getTotalElements());
    }
}
