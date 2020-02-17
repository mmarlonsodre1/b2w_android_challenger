package com.example.b2w_challenger.ui.contracts;

import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Pokemon;

public interface AbilityContract {
    interface AbilitiesRequestListener {
        void onAbilitySucess(AbilityInfo abilityInfo);
        void onPokemonSucess(Pokemon pokemon);
        void onError(Throwable error);
        void onComplete();
    }

    interface AbilityPresenterInterface {
        void getAbility(int abilityId);
        void getPokemon(String pokemonName);
    }
}