package com.kadasoftware.gutenberg.service.impl;

import com.kadasoftware.gutenberg.service.LibrosService;
import com.kadasoftware.gutenberg.domain.Libros;
import com.kadasoftware.gutenberg.repository.LibrosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Libros.
 */
@Service
public class LibrosServiceImpl implements LibrosService{

    private final Logger log = LoggerFactory.getLogger(LibrosServiceImpl.class);
    
    @Inject
    private LibrosRepository librosRepository;
    
    /**
     * Save a libros.
     * 
     * @param libros the entity to save
     * @return the persisted entity
     */
    public Libros save(Libros libros) {
        log.debug("Request to save Libros : {}", libros);
        Libros result = librosRepository.save(libros);
        return result;
    }

    /**
     *  Get all the libros.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<Libros> findAll(Pageable pageable) {
        log.debug("Request to get all Libros");
        Page<Libros> result = librosRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one libros by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Libros findOne(String id) {
        log.debug("Request to get Libros : {}", id);
        Libros libros = librosRepository.findOne(id);
        return libros;
    }

    /**
     *  Delete the  libros by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Libros : {}", id);
        librosRepository.delete(id);
    }
}
