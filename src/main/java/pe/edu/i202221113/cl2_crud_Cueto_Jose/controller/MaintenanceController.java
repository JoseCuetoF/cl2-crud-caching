package pe.edu.i202221113.cl2_crud_Cueto_Jose.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDetailDto;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.dto.FilmDto;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.entity.Language;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.service.MaintenanceService;


import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @RequestMapping("/start")
    public String start(Model model){

        List<FilmDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films",films);
        return "maintenance";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto",filmDetailDto);
        return "maintenance-detail";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance-edit";
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/add")
    public String add(Model model) {
        // Se agrega un FilmDetailDto vacío para representar un nuevo formulario
        FilmDetailDto filmDetailDto = new FilmDetailDto(
                null,
                "",
                "",
                null,
                null,
                "English",
                null,
                null,
                null,
                null,
                "",
                "",
                new Date()
        );

        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-add";
    }

    @PostMapping("/add")
    public String addFilm(@ModelAttribute FilmDetailDto filmDetailDto) {
        // Llamar al servicio para guardar la película
        maintenanceService.addFilm(filmDetailDto);

        // Redirigir a otra página (por ejemplo, a la lista de películas o página de inicio)
        return "redirect:/maintenance/start";  // O la URL que desees
    }



    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        System.out.println("Eliminando " + id);
        return "redirect:/maintenance/start";
    }

}