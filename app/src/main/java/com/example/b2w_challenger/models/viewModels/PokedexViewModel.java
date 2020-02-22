package com.example.b2w_challenger.models.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.b2w_challenger.models.Pokedex;

import java.util.List;

public class PokedexViewModel extends ViewModel {
    private MutableLiveData<List<Pokedex.PokemonSimple>> pokedex;

    public MutableLiveData<List<Pokedex.PokemonSimple>> getPokedex() {
        if (pokedex == null) {
            pokedex = new MutableLiveData<List<Pokedex.PokemonSimple>>();
        }
        return pokedex;
    }
}
