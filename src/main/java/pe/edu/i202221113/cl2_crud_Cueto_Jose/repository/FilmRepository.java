package pe.edu.i202221113.cl2_crud_Cueto_Jose.repository;


import jakarta.persistence.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.CrudRepository;

import pe.edu.i202221113.cl2_crud_Cueto_Jose.entity.Film;

public interface FilmRepository extends CrudRepository<Film,Integer> {




    @CacheEvict(value = "films", allEntries = true)
    Film save(Film film);

}
