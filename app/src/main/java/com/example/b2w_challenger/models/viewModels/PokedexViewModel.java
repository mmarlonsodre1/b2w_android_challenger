package com.example.b2w_challenger.models.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.b2w_challenger.models.Pokedex;

public class PokedexViewModel extends ViewModel {
    private MutableLiveData<Pokedex> pokedex;

    public MutableLiveData<Pokedex> getPokedex() {
        if (pokedex == null) {
            pokedex = new MutableLiveData<Pokedex>();
        }
        return pokedex;
    }
}
