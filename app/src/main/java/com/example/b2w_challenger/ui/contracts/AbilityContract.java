package com.example.b2w_challenger.ui.contracts;

import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Evolution;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.models.Specie;

public interface AbilityContract {
    interface AbilitiesRequestListener {
        void onAbilitySucess(AbilityInfo abilityInfo);
        void onPokemonSucess(Pokemon pokemon);
        void onPokemonSpecieSucess(Specie specie);
        void onPokemonEvolutionSucess(Evolution evolution);
        void onError(Throwable error);
        void onComplete();
    }

    interface AbilityPresenterInterface {
        void getAbility(int abilityId);
        void getPokemon(String pokemonName);
        void getPokemonSpecie(String pokemonName);
        void getPokemonEvolution(int idEvolution);
    }
}