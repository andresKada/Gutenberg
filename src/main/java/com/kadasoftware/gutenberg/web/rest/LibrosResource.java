package com.kadasoftware.gutenberg.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kadasoftware.gutenberg.domain.Libros;
import com.kadasoftware.gutenberg.service.LibrosService;
import com.kadasoftware.gutenberg.web.rest.util.HeaderUtil;
import com.kadasoftware.gutenberg.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Libros.
 */
@RestController
@RequestMapping("/api")
public class LibrosResource {

    private final Logger log = LoggerFactory.getLogger(LibrosResource.class);
        
    @Inject
    private LibrosService librosService;
    
    /**
     * POST  /libros : Create a new libros.
     *
     * @param libros the libros to create
     * @return the ResponseEntity with status 201 (Created) and with body the new libros, or with status 400 (Bad Request) if the libros has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/libros",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Libros> createLibros(@Valid @RequestBody Libros libros) throws URISyntaxException {
        log.debug("REST request to save Libros : {}", libros);
        if (libros.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("libros", "idexists", "A new libros cannot already have an ID")).body(null);
        }
        Libros result = librosService.save(libros);
        return ResponseEntity.created(new URI("/api/libros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("libros", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /libros : Updates an existing libros.
     *
     * @param libros the libros to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated libros,
     * or with status 400 (Bad Request) if the libros is not valid,
     * or with status 500 (Internal Server Error) if the libros couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/libros",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Libros> updateLibros(@Valid @RequestBody Libros libros) throws URISyntaxException {
        log.debug("REST request to update Libros : {}", libros);
        if (libros.getId() == null) {
            return createLibros(libros);
        }
        Libros result = librosService.save(libros);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("libros", libros.getId().toString()))
            .body(result);
    }

    /**
     * GET  /libros : get all the libros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of libros in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/libros",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Libros>> getAllLibros(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Libros");
        Page<Libros> page = librosService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/libros");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /libros/:id : get the "id" libros.
     *
     * @param id the id of the libros to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the libros, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/libros/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Libros> getLibros(@PathVariable String id) {
        log.debug("REST request to get Libros : {}", id);
        Libros libros = librosService.findOne(id);
        return Optional.ofNullable(libros)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /libros/:id : delete the "id" libros.
     *
     * @param id the id of the libros to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/libros/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLibros(@PathVariable String id) {
        log.debug("REST request to delete Libros : {}", id);
        librosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("libros", id.toString())).build();
    }

}
