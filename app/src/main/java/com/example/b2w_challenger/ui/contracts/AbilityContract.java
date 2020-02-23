package com.example.b2w_challenger.ui.contracts;

import com.example.b2w_challenger.models.Ability.AbilityInfo;
import com.example.b2w_challenger.models.Evolution.Evolution;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.Specie.Specie;

public interface AbilityContract {
    interface AbilitiesRequestListener {
        void onAbilitySucess(AbilityInfo abilityInfo);
        void onPokemonSucess(Pokemon pokemon);
        void onPokemonSucess(Pokemon pokemon, int index);
        void onPokemonSpecieSucess(Specie specie);
        void onPokemonEvolutionSucess(Evolution evolution);
        void onError(Throwable error);
        void onComplete();
    }

    interface AbilityPresenterInterface {
        void getAbility(int abilityId);
        void getPokemon(String pokemonName);
        void getPokemonVector(String pokemonName, int index);
        void getPokemonSpecie(String pokemonName);
        void getPokemonEvolution(int idEvolution);
    }
}