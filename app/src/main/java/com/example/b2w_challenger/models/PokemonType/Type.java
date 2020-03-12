package com.example.b2w_challenger.models.PokemonType;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Type {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("pokemon")
    private List<PokemonList> pokemon;

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
