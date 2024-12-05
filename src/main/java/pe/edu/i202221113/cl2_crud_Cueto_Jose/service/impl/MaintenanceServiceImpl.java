package pe.edu.i202221113.cl2_crud_Cueto_Jose.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDetailDto;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDto;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.entity.Film;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.entity.Language;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.repository.FilmRepository;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.repository.LanguageRepository;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.service.MaintenanceService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<FilmDto> getAllFilms() {

        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;
    }

    @Override
    public FilmDetailDto getFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);


        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean addFilm(FilmDetailDto filmDetailDto) {
        Film newFilm = new Film();
        newFilm.setTitle(filmDetailDto.title());
        newFilm.setDescription(filmDetailDto.description());
        newFilm.setReleaseYear(filmDetailDto.releaseYear());
        Language language = languageRepository.findById(filmDetailDto.languageId())
                .orElseThrow(() -> new RuntimeException("Lenguage no encontrado con ese ID: " + filmDetailDto.languageId()));
        newFilm.setLanguage(language);
        newFilm.setRentalDuration(filmDetailDto.rentalDuration());
        newFilm.setRentalRate(filmDetailDto.rentalRate());
        newFilm.setLength(filmDetailDto.length());
        newFilm.setReplacementCost(filmDetailDto.replacementCost());
        newFilm.setRating(filmDetailDto.rating());
        newFilm.setSpecialFeatures(filmDetailDto.specialFeatures());
        newFilm.setLastUpdate(new Date());

        filmRepository.save(newFilm);
        return true;
    }


}