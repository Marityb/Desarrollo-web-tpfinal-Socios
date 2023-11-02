package ar.org.centro35.herramientas.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.org.centro35.herramientas.entities.Herramienta;

@Repository
public interface HerramientaRepository extends CrudRepository<Herramienta, Integer> {
    public abstract List<Herramienta> findByDescripcion(String descripcion);

    /*
     * formas de escribir las queries en el nombre del método
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query
     * -methods.query-creation
     * 
     */

}
