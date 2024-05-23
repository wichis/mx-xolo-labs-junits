package com.xolo.labs.junit.clients.calls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.xolo.labs.junit.clients.calls.models.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * PokemonClient
 * Usando autowired con setter injection
 */
@Component
public class PokemonClientWithJackson {
    private WebClient client;
    private String host;
    private String basePathV2;
    private String getPokemonEndpoint;


    public Mono<ObjectMapper> getPokemon() {
        return this.client.get().uri(basePathV2.concat(getPokemonEndpoint)).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ObjectMapper.class);
    }

    @Autowired
    public void setHost(@Value("${pokemon.api.url.host}") String host) {
        this.host = host;
    }

    @Autowired
    public void setBasePathV2(@Value("${pokemon.api.url.base-path-v2}") String basePathV2) {
        this.basePathV2 = basePathV2;
    }

    @Autowired
    public void setGetPokemonEndpoint(@Value("${pokemon.api.url.endpoint.get-pokemon}") String getPokemonEndpoint) {
        this.getPokemonEndpoint = getPokemonEndpoint;
    }

    @Autowired
    public void setClient(WebClient.Builder builder) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.client = builder.baseUrl(host).build();
    }


}
