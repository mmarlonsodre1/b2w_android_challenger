package com.example.b2w_challenger.ui.contracts;

import android.view.View;

import com.example.b2w_challenger.models.Pokedex;

public interface PokedexContract {
    interface PokedexRequestListener {
        void onPokedexSucess(Pokedex pokedex);
        void onNextPokedexSucess(Pokedex pokedex);
        void onError(Throwable error);
        void onComplete();
    }

    interface PokedexPresenterInterface {
        void getPokedex();
        void getNextPokedex(int offset, int limit);
    }

    interface PokedexClickListener {
        void onPokemonClick(Pokedex.PokemonSimple pokemon, View view);
    }
}