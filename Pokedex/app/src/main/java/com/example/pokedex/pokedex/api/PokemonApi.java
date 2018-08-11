package com.example.pokedex.pokedex.api;

import com.example.pokedex.pokedex.api.models.Pokemon;
import com.example.pokedex.pokedex.api.models.PokemonSpritesBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {

    @GET("v1/sprite/?limit=151&offset=1")
    Call<PokemonSpritesBody> getKantoPokemons();

    @GET("v1/sprite/?limit=100&offset=152")
    Call<PokemonSpritesBody> getJohtoPokemons();

    @GET("v1/sprite/?limit=135&offset=252")
    Call<PokemonSpritesBody> getHoennPokemons();

    @GET("v1/sprite/?limit=108&offset=387")
    Call<PokemonSpritesBody> getSinnohPokemons();

    @GET("v2/pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);
}
