package co.com.dvc.commons.mapper;

import java.util.List;

public interface IGenericMapper<M, E> {

    E modelToEntity(M model);

    M entityToModel(E entity);

    default List<E> modelToEntityList(List<M> models) {
        return models.stream().map(this::modelToEntity).toList();
    }

    default List<M> entityToModelList(List<E> entities) {
        return entities.stream().map(this::entityToModel).toList();
    }

    default List<M> toModelList(List<E> entities) {
        return entityToModelList(entities);
    }

    default List<E> toEntityList(List<M> models) {
        return modelToEntityList(models);
    }
}
