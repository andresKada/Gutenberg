package com.kadasoftware.gutenberg.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kadasoftware.gutenberg.domain.Libros;
import com.kadasoftware.gutenberg.service.LibrosService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.inject.Inject;

/**
 * Created by ernesto. Date: 7/9/16. Time: 11:46 AM. Project: gutenberg. Package:
 * com.kadasoftware.gutenberg.web.rest.
 */
@RestController
@RequestMapping("/api")
public class PrinterBookResource {

    private final Logger log = LoggerFactory.getLogger(PrinterBookResource.class);

    @Inject
    private LibrosService librosService;

    @RequestMapping(value = "/printerbook",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Libros> getLibros() {
        log.debug("REST request to get the following book to print");

        Libros libros;
        do {
            libros = librosService.findByConsecutivo();
        } while (null == libros);

        return Optional.of(libros)
                       .map(result -> new ResponseEntity<>(
                           result,
                           HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
