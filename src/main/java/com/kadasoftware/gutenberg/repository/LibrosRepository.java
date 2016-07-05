package com.kadasoftware.gutenberg.repository;

import com.kadasoftware.gutenberg.domain.Libros;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Libros entity.
 */
@SuppressWarnings("unused")
public interface LibrosRepository extends MongoRepository<Libros,String> {

}
