package pe.edu.i202221113.cl2_crud_Cueto_Jose.service;

import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDetailDto;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDto;

import java.util.List;

public interface MaintenanceService {


    List<FilmDto> getAllFilms();

    FilmDetailDto getFilmById(int id);

    Boolean updateFilm(FilmDetailDto filmDetailDto);

    Boolean addFilm(FilmDetailDto filmDetailDto);

}
