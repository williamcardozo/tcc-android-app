package com.example.pokedex.pokedex.api.models;

import java.io.Serializable;

public class PokemonSprite implements Serializable {

    private Pokemon pokemon;
    private String image;
    private int id;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokemonSprite pokemon = (PokemonSprite) o;

        return image != null ? image.equals(pokemon.image) : pokemon.image == null;
    }
}
