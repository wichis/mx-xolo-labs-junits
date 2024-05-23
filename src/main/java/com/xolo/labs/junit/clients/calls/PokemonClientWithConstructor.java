package com.xolo.labs.junit.clients.calls;

import com.xolo.labs.junit.clients.calls.models.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * PokemonClient
 * Usando constructor injection
 */
@Component
public class PokemonClientWithConstructor {
    private final WebClient client;

    private String host;
    @Value("${pokemon.api.url.base-path-v2}")
    private String basePathV2;
    @Value("${pokemon.api.url.endpoint.get-pokemon}")
    private String getPokemonEndpoint;

    public PokemonClientWithConstructor(WebClient.Builder builder, @Value("${pokemon.api.url.host}") String host) {
        this.client = builder.baseUrl(host).build();
    }

    public Mono<String> getPokemonString() {
        return this.client.get().uri(basePathV2.concat(getPokemonEndpoint)).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .map(PokemonDto::toString);
    }

    public Mono<PokemonDto> getPokemonObject() {
        return this.client.get().uri(basePathV2.concat(getPokemonEndpoint)).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokemonDto.class);
    }

    public Mono<PokemonDto> getPokemonJackson() {
        return this.client.get().uri(basePathV2.concat(getPokemonEndpoint)).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PokemonDto.class);
    }

}
