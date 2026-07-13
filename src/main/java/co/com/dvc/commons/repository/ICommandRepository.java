package co.com.dvc.commons.repository;

public interface ICommandRepository<E, K> {

    E save(E entity);

    Iterable<E> saveAll(Iterable<E> entities);

    E update(E entity);

    Iterable<E> updateAll(Iterable<E> entities);

    void delete(K id);

    void deleteAll(Iterable<K> ids);
}
