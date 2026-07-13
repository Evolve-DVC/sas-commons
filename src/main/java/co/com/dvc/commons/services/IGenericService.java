package co.com.dvc.commons.services;

import co.com.dvc.commons.dto.request.PaginationRequest;
import co.com.dvc.commons.services.pageable.IPageableResult;

import java.util.List;

public interface IGenericService<M, K> {

    IPageableResult<M> getComboGrande(M model, PaginationRequest pagination);

    List<M> getComboSencillo(M model);

    M getElement(K id);

    M save(M model);

    Iterable<M> saveAll(Iterable<M> models);

    M update(M model);

    Iterable<M> updateAll(Iterable<M> models);

    M delete(M model);

    boolean existById(K id);

    K getNextId();
}
