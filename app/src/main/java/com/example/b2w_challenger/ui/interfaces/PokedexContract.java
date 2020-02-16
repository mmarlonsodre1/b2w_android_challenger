package com.example.b2w_challenger.ui.interfaces;

import com.example.b2w_challenger.models.Pokedex;

public interface PokedexContract {
    interface PokedexRequest{
        void onPokedexSucess(Pokedex pokedex);
        void onNextPokedexSucess(Pokedex pokedex);
        void onError(Throwable error);
        void onComplete();
    }

    interface PokedexPresenterInterface {
        void getPokedex();
        void getNextPokedex(int offset, int limit);
    }
}