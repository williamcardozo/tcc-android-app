package com.example.pokedex.pokedex.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex.pokedex.DrawerOption;
import com.example.pokedex.pokedex.PokemonDetailActivity;
import com.example.pokedex.pokedex.R;
import com.example.pokedex.pokedex.adapter.PokemonAdapter;
import com.example.pokedex.pokedex.api.PokemonService;
import com.example.pokedex.pokedex.api.models.PokemonSprite;
import com.example.pokedex.pokedex.api.models.PokemonSpritesBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment  implements Callback<PokemonSpritesBody>,PokemonAdapter.Listener {
    private static final String DRAWER_OPTION_KEY = "DRAWER_OPTION";
    private RecyclerView rvList;
    private DrawerOption drawerOption;

    public static ListFragment newInstance(DrawerOption drawerOption) {
        Bundle args = new Bundle();
        args.putString(DRAWER_OPTION_KEY, drawerOption.name());
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvList = view.findViewById(R.id.rv_list);
        drawerOption = DrawerOption.valueOf(getArguments().getString(DRAWER_OPTION_KEY));

        switch (drawerOption) {
            case KANTO:
                PokemonService.getInstance().getKantoPokemons().enqueue(this);
                break;
            case JOHTO:
                PokemonService.getInstance().getJohtoPokemons().enqueue(this);
                break;
            case HOENN:
                PokemonService.getInstance().getHoennPokemons().enqueue(this);
                break;
            case SINNOH:
                PokemonService.getInstance().getSinnohPokemons().enqueue(this);
                break;
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        PokemonAdapter adapter = (PokemonAdapter) rvList.getAdapter();

        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onPokemonClicked(PokemonSprite pokemonSprite) {
        Intent intent = new Intent(getContext(), PokemonDetailActivity.class);
        intent.putExtra(PokemonDetailActivity.POKEMON_EXTRA_KEY, pokemonSprite);
        startActivity(intent);
    }

    @Override
    public void onResponse(Call<PokemonSpritesBody> call, Response<PokemonSpritesBody> response) {
        List<PokemonSprite> pokemonSpriteList = response.body().getObjects();
        displayPokemonsSprites(pokemonSpriteList);
    }

    private void displayPokemonsSprites(List<PokemonSprite> pokemonSpriteList) {
        PokemonAdapter adapter = new PokemonAdapter(pokemonSpriteList, this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<PokemonSpritesBody> call, Throwable t) {
        Log.e("Pokemon", t.getLocalizedMessage(), t);
    }
}
