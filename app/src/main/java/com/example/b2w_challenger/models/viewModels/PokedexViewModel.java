package com.example.b2w_challenger.models.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.b2w_challenger.models.Pokedex.PokemonSimple;

import java.util.List;

public class PokedexViewModel extends ViewModel {
    private MutableLiveData<List<PokemonSimple>> pokedex;

    public MutableLiveData<List<PokemonSimple>> getPokedex() {
        if (pokedex == null) {
            pokedex = new MutableLiveData<List<PokemonSimple>>();
        }
        return pokedex;
    }
}
