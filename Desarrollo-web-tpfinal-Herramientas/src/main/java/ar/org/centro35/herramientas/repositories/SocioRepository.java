package ar.org.centro35.herramientas.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.org.centro35.herramientas.entities.Socio;


@Repository
public interface SocioRepository extends CrudRepository<Socio, Integer> {
    public abstract List<Socio> findByNombre(String nombre);
}