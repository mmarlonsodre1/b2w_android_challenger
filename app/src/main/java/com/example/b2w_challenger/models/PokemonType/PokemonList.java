package com.example.b2w_challenger.models.PokemonType;

import com.example.b2w_challenger.models.Pokedex.PokemonSimple;
import com.google.gson.annotations.SerializedName;

public class PokemonList {
    @SerializedName("pokemon")
    private PokemonSimple pokemon;

    public PokemonSimple getPokemon() {
        return pokemon;
    }
}
