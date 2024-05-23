package com.xolo.labs.junit.clients.calls.models.dto;

public class PokemonDto {
    private Integer id;
    private String name;

    private Number order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getOrder() {
        return order;
    }

    public void setOrder(Number order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PokemonDto{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", order=" + this.getOrder() +
                '}';
    }
}
