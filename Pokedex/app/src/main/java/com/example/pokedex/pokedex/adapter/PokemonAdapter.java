package com.example.pokedex.pokedex.adapter;

import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pokedex.pokedex.R;
import com.example.pokedex.pokedex.api.PokemonService;
import com.example.pokedex.pokedex.api.models.PokemonSprite;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{

    private List<PokemonSprite> pokemonSpriteList;
    private Listener listener;

    public PokemonAdapter(List<PokemonSprite> pokemonSpriteList, Listener listener) {
        this.pokemonSpriteList = pokemonSpriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder viewHolder, int i) {
        PokemonSprite pokemon = pokemonSpriteList.get(i);

        if(listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.onPokemonClicked(pokemon));
        }

        viewHolder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return this.pokemonSpriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPokemon;
        private AppCompatTextView tvPokemon;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPokemon =  itemView.findViewById(R.id.iv_Pokemon);
            tvPokemon = itemView.findViewById(R.id.tv_Pokemon);
        }

        public void bind(PokemonSprite pokemon) {
            Picasso.get().load(PokemonService.BASE_URL + pokemon.getImage()).into(ivPokemon);
            tvPokemon.setText(pokemon.getPokemon().getName());
        }
    }

    public interface Listener {
        void onPokemonClicked(PokemonSprite pokemon);
    }
}
