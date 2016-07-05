package com.kadasoftware.gutenberg.domain;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Libros libros = (Libros) o;
        if(libros.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, libros.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Libros{" +
            "id=" + id +
            ", nombre_libro='" + nombre_libro + "'" +
            ", nombre_autor='" + nombre_autor + "'" +
            ", editorial='" + editorial + "'" +
            ", fragmento_libro='" + fragmento_libro + "'" +
            ", stan_ubicacion='" + stan_ubicacion + "'" +
            '}';
    }
}
