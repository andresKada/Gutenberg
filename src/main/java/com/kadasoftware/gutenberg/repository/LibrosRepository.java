package com.kadasoftware.gutenberg.repository;

import com.kadasoftware.gutenberg.domain.Libros;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Libros entity.
 */
public interface LibrosRepository extends MongoRepository<Libros,String> {

    Libros findByConsecutivo(Integer consecutivo);
}
