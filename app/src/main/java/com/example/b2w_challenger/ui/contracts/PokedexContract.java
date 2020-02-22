package com.example.b2w_challenger.ui.contracts;

import android.view.View;

import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;

public interface PokedexContract {
    interface PokedexRequestListener {
        void onBefore();
        void onPokedexSucess(Pokedex pokedex);
        void onNextPokedexSucess(Pokedex pokedex);
        void onPokemonSucess(Pokemon pokemon);
        void onError(Throwable error);
        void OnPokemonError(Throwable error);
        void onComplete();
    }

    interface PokedexPresenterInterface {
        void getPokedex();
        void getNextPokedex(int offset, int limit);
        void getPokemon(String pokemonName);
    }

    interface PokedexClickListener {
        void onPokemonClick(Pokedex.PokemonSimple pokemon, View view);
    }
}