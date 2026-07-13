package co.com.dvc.commons.cqrs;

import co.com.dvc.commons.services.pageable.IPageableResult;
import co.com.dvc.commons.util.PaginationHelper;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public abstract class PaginatedQueryAbstract<C, M, R> extends QueryAbstract<C, Page<R>> {

    @Override
    protected Page<R> process(C context) {
        IPageableResult<M> rawResult = fetchPage(context);

        Function<M, R> mapper = getMapper();

        return PaginationHelper.mapPage(rawResult, mapper);
    }

    protected abstract IPageableResult<M> fetchPage(C context);

    protected abstract Function<M, R> getMapper();
}
