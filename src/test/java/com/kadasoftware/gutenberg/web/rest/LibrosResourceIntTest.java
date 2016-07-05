package com.kadasoftware.gutenberg.web.rest;

import com.kadasoftware.gutenberg.GutenbergApp;
import com.kadasoftware.gutenberg.domain.Libros;
import com.kadasoftware.gutenberg.repository.LibrosRepository;
import com.kadasoftware.gutenberg.service.LibrosService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the LibrosResource REST controller.
 *
 * @see LibrosResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GutenbergApp.class)
@WebAppConfiguration
@IntegrationTest
public class LibrosResourceIntTest {

    private static final String DEFAULT_NOMBRE_LIBRO = "AAAA";
    private static final String UPDATED_NOMBRE_LIBRO = "BBBB";
    private static final String DEFAULT_NOMBRE_AUTOR = "AAAA";
    private static final String UPDATED_NOMBRE_AUTOR = "BBBB";
    private static final String DEFAULT_EDITORIAL = "AAAA";
    private static final String UPDATED_EDITORIAL = "BBBB";
    private static final String DEFAULT_FRAGMENTO_LIBRO = "AAAA";
    private static final String UPDATED_FRAGMENTO_LIBRO = "BBBB";
    private static final String DEFAULT_STAN_UBICACION = "AAAA";
    private static final String UPDATED_STAN_UBICACION = "BBBB";

    @Inject
    private LibrosRepository librosRepository;

    @Inject
    private LibrosService librosService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLibrosMockMvc;

    private Libros libros;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LibrosResource librosResource = new LibrosResource();
        ReflectionTestUtils.setField(librosResource, "librosService", librosService);
        this.restLibrosMockMvc = MockMvcBuilders.standaloneSetup(librosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        librosRepository.deleteAll();
        libros = new Libros();
        libros.setNombre_libro(DEFAULT_NOMBRE_LIBRO);
        libros.setNombre_autor(DEFAULT_NOMBRE_AUTOR);
        libros.setEditorial(DEFAULT_EDITORIAL);
        libros.setFragmento_libro(DEFAULT_FRAGMENTO_LIBRO);
        libros.setStan_ubicacion(DEFAULT_STAN_UBICACION);
    }

    @Test
    public void createLibros() throws Exception {
        int databaseSizeBeforeCreate = librosRepository.findAll().size();

        // Create the Libros

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isCreated());

        // Validate the Libros in the database
        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeCreate + 1);
        Libros testLibros = libros.get(libros.size() - 1);
        assertThat(testLibros.getNombre_libro()).isEqualTo(DEFAULT_NOMBRE_LIBRO);
        assertThat(testLibros.getNombre_autor()).isEqualTo(DEFAULT_NOMBRE_AUTOR);
        assertThat(testLibros.getEditorial()).isEqualTo(DEFAULT_EDITORIAL);
        assertThat(testLibros.getFragmento_libro()).isEqualTo(DEFAULT_FRAGMENTO_LIBRO);
        assertThat(testLibros.getStan_ubicacion()).isEqualTo(DEFAULT_STAN_UBICACION);
    }

    @Test
    public void checkNombre_libroIsRequired() throws Exception {
        int databaseSizeBeforeTest = librosRepository.findAll().size();
        // set the field null
        libros.setNombre_libro(null);

        // Create the Libros, which fails.

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isBadRequest());

        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNombre_autorIsRequired() throws Exception {
        int databaseSizeBeforeTest = librosRepository.findAll().size();
        // set the field null
        libros.setNombre_autor(null);

        // Create the Libros, which fails.

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isBadRequest());

        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEditorialIsRequired() throws Exception {
        int databaseSizeBeforeTest = librosRepository.findAll().size();
        // set the field null
        libros.setEditorial(null);

        // Create the Libros, which fails.

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isBadRequest());

        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFracmento_libroIsRequired() throws Exception {
        int databaseSizeBeforeTest = librosRepository.findAll().size();
        // set the field null
        libros.setFragmento_libro(null);

        // Create the Libros, which fails.

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isBadRequest());

        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStan_ubicacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = librosRepository.findAll().size();
        // set the field null
        libros.setStan_ubicacion(null);

        // Create the Libros, which fails.

        restLibrosMockMvc.perform(post("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(libros)))
                .andExpect(status().isBadRequest());

        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllLibros() throws Exception {
        // Initialize the database
        librosRepository.save(libros);

        // Get all the libros
        restLibrosMockMvc.perform(get("/api/libros?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(libros.getId())))
                .andExpect(jsonPath("$.[*].nombre_libro").value(hasItem(DEFAULT_NOMBRE_LIBRO.toString())))
                .andExpect(jsonPath("$.[*].nombre_autor").value(hasItem(DEFAULT_NOMBRE_AUTOR.toString())))
                .andExpect(jsonPath("$.[*].editorial").value(hasItem(DEFAULT_EDITORIAL.toString())))
                .andExpect(jsonPath("$.[*].fracmento_libro").value(hasItem(DEFAULT_FRAGMENTO_LIBRO.toString())))
                .andExpect(jsonPath("$.[*].stan_ubicacion").value(hasItem(DEFAULT_STAN_UBICACION.toString())));
    }

    @Test
    public void getLibros() throws Exception {
        // Initialize the database
        librosRepository.save(libros);

        // Get the libros
        restLibrosMockMvc.perform(get("/api/libros/{id}", libros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(libros.getId()))
            .andExpect(jsonPath("$.nombre_libro").value(DEFAULT_NOMBRE_LIBRO.toString()))
            .andExpect(jsonPath("$.nombre_autor").value(DEFAULT_NOMBRE_AUTOR.toString()))
            .andExpect(jsonPath("$.editorial").value(DEFAULT_EDITORIAL.toString()))
            .andExpect(jsonPath("$.fracmento_libro").value(DEFAULT_FRAGMENTO_LIBRO.toString()))
            .andExpect(jsonPath("$.stan_ubicacion").value(DEFAULT_STAN_UBICACION.toString()));
    }

    @Test
    public void getNonExistingLibros() throws Exception {
        // Get the libros
        restLibrosMockMvc.perform(get("/api/libros/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateLibros() throws Exception {
        // Initialize the database
        librosService.save(libros);

        int databaseSizeBeforeUpdate = librosRepository.findAll().size();

        // Update the libros
        Libros updatedLibros = new Libros();
        updatedLibros.setId(libros.getId());
        updatedLibros.setNombre_libro(UPDATED_NOMBRE_LIBRO);
        updatedLibros.setNombre_autor(UPDATED_NOMBRE_AUTOR);
        updatedLibros.setEditorial(UPDATED_EDITORIAL);
        updatedLibros.setFragmento_libro(UPDATED_FRAGMENTO_LIBRO);
        updatedLibros.setStan_ubicacion(UPDATED_STAN_UBICACION);

        restLibrosMockMvc.perform(put("/api/libros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedLibros)))
                .andExpect(status().isOk());

        // Validate the Libros in the database
        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeUpdate);
        Libros testLibros = libros.get(libros.size() - 1);
        assertThat(testLibros.getNombre_libro()).isEqualTo(UPDATED_NOMBRE_LIBRO);
        assertThat(testLibros.getNombre_autor()).isEqualTo(UPDATED_NOMBRE_AUTOR);
        assertThat(testLibros.getEditorial()).isEqualTo(UPDATED_EDITORIAL);
        assertThat(testLibros.getFragmento_libro()).isEqualTo(UPDATED_FRAGMENTO_LIBRO);
        assertThat(testLibros.getStan_ubicacion()).isEqualTo(UPDATED_STAN_UBICACION);
    }

    @Test
    public void deleteLibros() throws Exception {
        // Initialize the database
        librosService.save(libros);

        int databaseSizeBeforeDelete = librosRepository.findAll().size();

        // Get the libros
        restLibrosMockMvc.perform(delete("/api/libros/{id}", libros.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Libros> libros = librosRepository.findAll();
        assertThat(libros).hasSize(databaseSizeBeforeDelete - 1);
    }
}
