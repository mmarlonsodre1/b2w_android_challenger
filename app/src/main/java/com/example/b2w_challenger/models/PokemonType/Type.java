package com.example.b2w_challenger.models.PokemonType;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Type {
    @SerializedName("name")
    String name;

    @SerializedName("id")
    int id;

    @SerializedName("pokemon")
    List<PokemonList> pokemon;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<PokemonList> getPokemon() {
        return pokemon;
    }
}
