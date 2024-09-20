package com.example.inicial1;

import com.example.inicial1.entities.Domicilio;
import com.example.inicial1.entities.*;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private PersonaRepository personaRepository;
	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);

		System.out.println("funcionando");
	}




	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository) {
		return args -> {
	// Creo un objeto persona
Persona per1 = Persona.builder().
		nombre("Alberto").apellido("Cortez").
		build();

Domicilio dom1 = Domicilio.builder().
		calle("Suipacha").
		numero(239).build();

per1.setDomicilio(dom1);

			personaRepository.save(per1);

// Creo otra persona
			Persona per2 = Persona.builder().
					nombre("Alicia").apellido("Calderon").
					build();

			Domicilio dom2 = Domicilio.builder().
					calle("Lulunta").
					numero(339).build();

			per2.setDomicilio(dom2);


			// Lo grabo a través del repositorio de Spring
			personaRepository.save(per2);

			List<Persona> recuperadas = personaRepository.findAll();
			System.out.println(recuperadas);

			logger.info("Detalles de la persona: {}", recuperadas);




			Optional<Persona> recuperada = personaRepository.findById(1L);
			System.out.println(recuperada);

			logger.info("Detalles de la persona: {}", recuperada);


			dom1.setCalle("Rodriguezaaaa");

			personaRepository.save(per1);

			//Creamos un autor con builder
			Autor autor1 = Autor.builder()
					.nombre("J.R.R.")
					.apellido("Tolkien")
					.biografia("Escritor británico")
					.build();

			// Creamos un libro con Builder
			Libro libro1 = Libro.builder()
					.titulo("El Señor de los Anillos")
					.fecha(1954)
					.genero("Fantasía")
					.paginas(500)
					.build();

			libro1.getAutores().add(autor1);
			// Agregamos el libro a la persona
			per1.getLibros().add(libro1);

			// Creamos otro autor con Builder
			Autor autor2 = Autor.builder()
					.nombre("George")
					.apellido("Orwell")
					.biografia("Otro escritor")
					.build();

			// Creamos otro libro con Builder
			Libro libro2 = Libro.builder()
					.titulo("1984")
					.fecha(1949)
					.genero("Ciencia Ficción")
					.paginas(300)
					.build();

			// Agregamos el autor al libro
			libro2.getAutores().add(autor2);
			// Agregamos el libro a la persona 2
			per2.getLibros().add(libro2);
		};

		};




}
