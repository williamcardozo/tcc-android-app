package com.example.pokedex.pokedex.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonService {
    public static final String BASE_URL = "https://pokeapi.co";
    private static final String BASE_API_URL = "https://pokeapi.co/api/";
    private static PokemonApi instance;

    private PokemonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        instance = retrofit.create(PokemonApi.class);
    }

    /**
     * Gets the MagicApi instance.
     *
     * @return MagicApi instance.
     */
    public static PokemonApi getInstance() {
        if(instance == null) {
            new PokemonService();
        }

        return instance;
    }
}
