package com.example.pokedex.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.widget.ImageView;

import com.example.pokedex.pokedex.api.PokemonService;
import com.example.pokedex.pokedex.api.models.Pokemon;
import com.example.pokedex.pokedex.api.models.PokemonSprite;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    public static final String POKEMON_EXTRA_KEY = "POKEMON_EXTRA_KEY";

    private ImageView pokemonDetailImage;
    private AppCompatTextView pokemonDetailName;
    private AppCompatTextView pokemonHp;
    private AppCompatTextView pokemonAttack;
    private AppCompatTextView pokemonDefense;
    private AppCompatTextView pokemonSpecialAttack;
    private AppCompatTextView pokemonSpecialDefense;
    private AppCompatTextView pokemonSpeed;
    private AppCompatTextView pokemonDetailType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        PokemonSprite pokemonSprite = (PokemonSprite) getIntent().getSerializableExtra(POKEMON_EXTRA_KEY);

        pokemonDetailImage = findViewById(R.id.pokemon_detail_iv);
        Picasso.get().load(PokemonService.BASE_URL + pokemonSprite.getImage()).into(pokemonDetailImage);

        pokemonDetailName = findViewById(R.id.pokemon_detail_name);
        pokemonDetailName.setText(String.format("#%s %s", pokemonSprite.getId(), pokemonSprite.getPokemon().getName()));

        pokemonHp = findViewById(R.id.pokemon_hp);
        pokemonAttack = findViewById(R.id.pokemon_attack);
        pokemonDefense = findViewById(R.id.pokemon_defense);
        pokemonSpecialAttack = findViewById(R.id.pokemon_special_attack);
        pokemonSpecialDefense = findViewById(R.id.pokemon_special_defense);
        pokemonSpeed = findViewById(R.id.pokemon_speed);
        pokemonDetailType = findViewById(R.id.pokemon_type);

        PokemonService.getInstance().getPokemon(pokemonSprite.getId()).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                handlePokemonSuccessResponse(response.body());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("Pokemon", t.getLocalizedMessage(), t);
            }
        });
    }

    private void handlePokemonSuccessResponse(Pokemon pokemon) {
        pokemonDetailType.setText(pokemon.getStringTypes());

        pokemonHp.setText(pokemon.getBaseStat("HP"));
        pokemonAttack.setText(pokemon.getBaseStat("Ataque"));
        pokemonDefense.setText(pokemon.getBaseStat("Defesa"));
        pokemonSpecialAttack.setText(pokemon.getBaseStat("Ataque especial"));
        pokemonSpecialDefense.setText(pokemon.getBaseStat("Defesa especial"));
        pokemonSpeed.setText(pokemon.getBaseStat("Velocidade"));
    }

}
