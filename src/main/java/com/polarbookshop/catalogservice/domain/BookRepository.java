package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long>{
    // Don't need these as they are now supplied by CrudRepository
    //Iterable<Book> findAll();
    //Book save(Book book);

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    
    @Modifying
    @Transactional
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
