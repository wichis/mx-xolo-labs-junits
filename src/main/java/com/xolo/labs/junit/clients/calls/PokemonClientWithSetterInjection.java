package com.xolo.labs.junit.clients.calls;

import com.xolo.labs.junit.clients.calls.models.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * PokemonClient
 * Usando autowired con setter injection
 */
@Component
public class PokemonClientWithSetterInjection {
    private WebClient client;
    private String host;
    private String basePathV2;
    private String getPokemonEndpoint;


    public Mono<String> getPokemon() {
        return this.client.get().uri(basePathV2.concat(getPokemonEndpoint)).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .map(PokemonDto::toString);
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
        this.client = builder.baseUrl(host).build();
    }


}
