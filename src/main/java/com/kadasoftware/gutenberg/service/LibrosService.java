package com.kadasoftware.gutenberg.service;

import com.kadasoftware.gutenberg.domain.Libros;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Libros.
 */
public interface LibrosService {

    /**
     * Save a libros.
     * 
     * @param libros the entity to save
     * @return the persisted entity
     */
    Libros save(Libros libros);

    /**
     *  Get all the libros.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Libros> findAll(Pageable pageable);

    /**
     *  Get the "id" libros.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Libros findOne(String id);

    /**
     *  Delete the "id" libros.
     *  
     *  @param id the id of the entity
     */
    void delete(String id);
}
