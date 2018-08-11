package com.example.pokedex.pokedex.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pokemon implements Serializable {
    private String name, id;
    private List<PokemonType> types;
    private List<PokemonStat> stats;

    public String getName() {
        String nameNormalized = Character.toUpperCase(this.name.charAt(0)) + this.name.substring(1);

        return nameNormalized;
    }

    public String getId() { return id; }

    public List<PokemonType> getTypes() {
        return types;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }


    public String getBaseStat(String statName) {
        for (PokemonStat stat: this.stats)
            if(stat.getStat().getName().equals(statName))
                return String.valueOf(stat.getBaseStat());

        return "0";
    }

    public String getStringTypes() {
        List<String> typeNames = new ArrayList<String>();

        for (PokemonType pokeType : this.getTypes())
            typeNames.add(pokeType.getType().getName());

        return String.join("\\", typeNames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        return id != null ? id.equals(pokemon.id) : pokemon.id == null;
    }
}
