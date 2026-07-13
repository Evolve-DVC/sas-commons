package co.com.dvc.commons.services.impl;

import co.com.dvc.commons.dto.request.PaginationRequest;
import co.com.dvc.commons.repository.IRepository;
import co.com.dvc.commons.services.IGenericService;
import co.com.dvc.commons.services.pageable.IPageableResult;
import co.com.dvc.commons.services.pageable.PageableResultImpl;

import java.util.List;

public abstract class GenericServiceImpl<M, K> implements IGenericService<M, K> {

    protected abstract IRepository<M, K> getRepository();

    @Override
    public IPageableResult<M> getComboGrande(M model, PaginationRequest pagination) {
        return new PageableResultImpl<>(List.of(), 0, 1, 0L);
    }

    @Override
    public List<M> getComboSencillo(M model) {
        return List.of();
    }

    @Override
    public M getElement(K id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public M save(M model) {
        return getRepository().save(model);
    }

    @Override
    public Iterable<M> saveAll(Iterable<M> models) {
        return getRepository().saveAll(models);
    }

    @Override
    public M update(M model) {
        return getRepository().update(model);
    }

    @Override
    public Iterable<M> updateAll(Iterable<M> models) {
        return getRepository().saveAll(models);
    }

    @Override
    public M delete(M model) {
        return model;
    }

    @Override
    public boolean existById(K id) {
        return getRepository().existsById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public K getNextId() {
        return (K) Long.valueOf(0);
    }

    protected K getModelKey(M model) {
        return null;
    }

    protected M getEmptyModel() {
        return null;
    }
}
