package pe.edu.i202221113.cl2_crud_Cueto_Jose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.entity.Film;
import pe.edu.i202221113.cl2_crud_Cueto_Jose.repository.FilmRepository;

import java.util.Optional;

@SpringBootApplication
public class Cl2CrudCuetoJoseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Cl2CrudCuetoJoseApplication.class, args);
	}

	@Autowired
	FilmRepository filmRepository;


	@Override
	public void run(String... args) throws Exception {


		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("findAll() - 1ra llamada MySQL");
		System.out.println("-------------------------------");
		Iterable<Film> iterable = filmRepository.findAll();
		iterable.forEach((film) -> {
			System.out.print(String.format("%s:%s;", film.getFilmId(), film.getTitle()));
			//System.out.print(message);
		});

		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("findAll() - 2ra llamada MySQL");
		System.out.println("-------------------------------");
		Iterable<Film> iterable2 = filmRepository.findAll();
		iterable2.forEach((film) -> {
			System.out.print(String.format("%s:%s;", film.getFilmId(), film.getTitle()));
			//String message = String.format("%s:%s;", film.getFilmId(), film.getTitle());
			//System.out.print(message);
		});

		/**
		 * findAll - Caching - 3ra
		 */
		System.out.println(" ");
		System.out.println("-------------------------------");
		System.out.println("save() - Film");
		System.out.println("-------------------------------");
		Optional<Film> optional = filmRepository.findById(2);
		optional.ifPresentOrElse(
				(film) -> {
					film.setTitle("Stephanie Payasa");
					filmRepository.save(film);
				},
				() -> {
					System.out.println("Film not found");
				}
		);

		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("findAll() - 4ra llamada MySQL");
		System.out.println("-------------------------------");
		Iterable<Film> iterable3 = filmRepository.findAll();
		iterable3.forEach((film) -> {
			System.out.print(String.format("%s:%s;", film.getFilmId(), film.getTitle()));
		});
	}
}
