package com.example.inicial1;

import com.example.inicial1.entities.Domicilio;
import com.example.inicial1.entities.*;
import com.example.inicial1.repositories.AutorRepository;
import com.example.inicial1.repositories.LocalidadRepository;
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
import java.util.Set;

@SpringBootApplication
public class Inicial1Application {
	 private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);


	@Autowired
	private LocalidadRepository localidadRepository;
	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);

		System.out.println("funcionando");
	}
	@Bean
	@Transactional
	CommandLineRunner init(LocalidadRepository localidadRepository, PersonaRepository personaRepository, AutorRepository autorRepository) {
		return args -> {

			// Creo localidades
			Localidad loc1 = Localidad.builder()
					.denominacion("Guaymallén")
					.build();
			Localidad loc2 = Localidad.builder()
					.denominacion("Maipú")
					.build();
			Localidad loc3 = Localidad.builder()
					.denominacion("Ciudad")
					.build();
			localidadRepository.save(loc1);
			localidadRepository.save(loc2);
			localidadRepository.save(loc3);

			// Creo autores
			Autor autor1 = Autor.builder()
					.nombre("Jorge Luis")
					.apellido("Borges")
					.biografia("Escritor y poeta argentino, conocido por sus obras como 'Ficciones' y 'El Aleph'.")
					.build();
			autorRepository.save(autor1);

			Autor autor2 = Autor.builder()
					.nombre("Gabriel")
					.apellido("García Márquez")
					.biografia("Escritor colombiano, premio Nobel de Literatura en 1982, conocido por sus obras como 'Cien años de soledad' y 'El amor en los tiempos del cólera'.")
					.build();
			autorRepository.save(autor2);

			// Creo domicilios
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Suipacha")
					.numero(239)
					.localidad(loc2)
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Lulunta")
					.numero(339)
					.localidad(loc1)
					.build();

			// Creo personas
			Persona persona1 = Persona.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.dni(12345678)
					.domicilio(domicilio1)
					.build();
			personaRepository.save(persona1);

			Persona persona2 = Persona.builder()
					.nombre("María")
					.apellido("González")
					.dni(23456789)
					.domicilio(domicilio2)
					.build();
			personaRepository.save(persona2);

			// Crea libros y asigna a personas y autores
			Libro libro1 = Libro.builder()
					.titulo("Ficciones")
					.fecha(1944)
					.genero("Cuento")
					.paginas(250)
					.autores(Set.of(autor1))
					.build();
			persona1.getLibros().add(libro1);
			personaRepository.save(persona1);

			Libro libro2 = Libro.builder()
					.titulo("Cien años de soledad")
					.fecha(1967)
					.genero("Novela")
					.paginas(450)
					.autores(Set.of(autor2))
					.build();
			persona2.getLibros().add(libro2);
			personaRepository.save(persona2);

			Libro libro3 = Libro.builder()
					.titulo("El Aleph")
					.fecha(1949)
					.genero("Cuento")
					.paginas(200)
					.autores(Set.of(autor1))
					.build();
			persona1.getLibros().add(libro3);
			personaRepository.save(persona1);

			List<Persona> recuperadas = personaRepository.findAll();
			System.out.println(recuperadas);

			logger.info("Detalles de la persona: {}", recuperadas);

			Optional<Persona> recuperada = personaRepository.findById(1L);
			System.out.println(recuperada);

			logger.info("Detalles de la persona: {}", recuperada);


		};
	}



/*




		};
*/





}
