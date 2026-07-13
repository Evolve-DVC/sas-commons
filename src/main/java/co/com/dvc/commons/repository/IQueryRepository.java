package co.com.dvc.commons.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IQueryRepository<E, K> {

    Page<E> findAll(Example<E> example, Pageable pageable);

    List<E> findAll(Example<E> example);

    Optional<E> findById(K id);

    boolean existsById(K id);

    K getNextValSequence();
}
