package com.xolo.labs.junit;

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

		ConfigurableApplicationContext context = SpringApplication.run(MxXoloLabsJunitsApplication.class, args);
		logger.info("=== INICIO =====================================");

		logger.info(new Greetings().saludar(LenguageType.ENGLISH));
		logger.info(new Greetings().saludar(LenguageType.SPANISH));

		logger.info("===============================================");

		PokemonClientWithSetterInjection pokemonCli = context.getBean(PokemonClientWithSetterInjection.class);
		logger.info(pokemonCli.getPokemon().block());
		context.close();
		logger.info("=== FIN =====================================");
	}

}
