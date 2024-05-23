package com.xolo.labs.junit;

import com.xolo.labs.junit.clients.calls.PokemonClientWithConstructor;
import com.xolo.labs.junit.clients.calls.PokemonClientWithJackson;
import com.xolo.labs.junit.clients.calls.PokemonClientWithSetterInjection;
import com.xolo.labs.junit.libs.Greetings;
import com.xolo.labs.junit.libs.types.LenguageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MxXoloLabsJunitsApplication {

	static Logger logger = LoggerFactory.getLogger(MxXoloLabsJunitsApplication.class);

	public static void main(String[] args) {

		// Toma el contexto de la aplicación
		ConfigurableApplicationContext context = SpringApplication.run(MxXoloLabsJunitsApplication.class, args);
		logger.info("=== INICIO =====================================");

		logger.info(new Greetings().saludar(LenguageType.ENGLISH));
		logger.info(new Greetings().saludar(LenguageType.SPANISH));

		logger.info("===============================================");

//		PokemonClientWithConstructor pokemonCli2 = context.getBean(PokemonClientWithConstructor.class);
//		logger.info(pokemonCli2.getPokemonString().block());
//		logger.info("Pokemon name: {}",pokemonCli2.getPokemonObject().block().getName());
//
//		PokemonClientWithSetterInjection pokemonCli = context.getBean(PokemonClientWithSetterInjection.class);
//		logger.info(pokemonCli.getPokemon().block());

		PokemonClientWithJackson pokemonClientWithJackson = context.getBean(PokemonClientWithJackson.class);
		logger.info("Pokemon name > From object node: {}", pokemonClientWithJackson.getPokemon().block().createObjectNode().get("name").asText());

		context.close();// Closing the context
		logger.info("=== FIN =====================================");
	}

}
