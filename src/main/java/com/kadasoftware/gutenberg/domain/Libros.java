package com.kadasoftware.gutenberg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Libros.
 */

@Document(collection = "libros")
public class Libros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 4, max = 255)
    @Field("nombre_libro")
    private String nombre_libro;

    @NotNull
    @Size(min = 4, max = 255)
    @Field("nombre_autor")
    private String nombre_autor;

    @NotNull
    @Size(min = 4, max = 255)
    @Field("editorial")
    private String editorial;

    @NotNull
    @Size(min = 4, max = 500)
    @Field("fragmento_libro")
    private String fragmento_libro;

    @NotNull
    @Size(min = 4, max = 255)
    @Field("stan_ubicacion")
    private String stan_ubicacion;

    @Field("consecutivo")
    private Integer consecutivo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFragmento_libro() {
        return fragmento_libro;
    }

    public void setFragmento_libro(String fragmento_libro) {
        this.fragmento_libro = fragmento_libro;
    }

    public String getStan_ubicacion() {
        return stan_ubicacion;
    }

    public void setStan_ubicacion(String stan_ubicacion) {
        this.stan_ubicacion = stan_ubicacion;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(final Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, nombre_libro, nombre_autor, editorial, fragmento_libro, stan_ubicacion,
                  consecutivo);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Libros other = (Libros) obj;
        return Objects.equals(this.id, other.id) && Objects
            .equals(this.nombre_libro, other.nombre_libro) && Objects
            .equals(this.nombre_autor, other.nombre_autor) && Objects
            .equals(this.editorial, other.editorial) && Objects
            .equals(this.fragmento_libro, other.fragmento_libro) && Objects
            .equals(this.stan_ubicacion, other.stan_ubicacion) && Objects
            .equals(this.consecutivo, other.consecutivo);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("nombre_libro", nombre_libro)
                                        .append("nombre_autor", nombre_autor)
                                        .append("editorial", editorial)
                                        .append("fragmento_libro", fragmento_libro)
                                        .append("stan_ubicacion", stan_ubicacion)
                                        .append("consecutivo", consecutivo).toString();
    }
}
