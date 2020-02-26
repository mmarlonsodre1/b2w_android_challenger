package com.example.b2w_challenger.ui.contracts;

import android.view.View;

import androidx.navigation.fragment.FragmentNavigator;

import com.example.b2w_challenger.models.Pokedex.Pokedex;
import com.example.b2w_challenger.models.Pokedex.PokemonSimple;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.PokemonType.Type;

public interface PokedexContract {
    interface PokedexRequestListener {
        void onBefore();
        void onPokedexSucess(Pokedex pokedex);
        void onNextPokedexSucess(Pokedex pokedex);
        void onPokemonTypeSucess(Type pokemonType);
        void onPokemonSucess(Pokemon pokemon);
        void onError(Throwable error);
        void OnPokemonError(Throwable error);
        void onComplete();
    }

    interface PokedexPresenterInterface {
        void getPokedex();
        void getNextPokedex(int offset, int limit);
        void getPokemon(String pokemonName);
        void getPokemonType(String type);
    }

    interface PokedexClickListener {
        void onPokemonClick(PokemonSimple pokemon, View view,
                            FragmentNavigator.Extras extras);
    }
}