package com.example.b2w_challenger.models.Specie;

import com.google.gson.annotations.SerializedName;

public class Specie {
    @SerializedName("evolution_chain")
    private EvolutionSpecie evolutionSpecie;

    @SerializedName("name")
    private String name;

    public EvolutionSpecie getEvolutionSpecie() {
        return evolutionSpecie;
    }

    public String getName() {
        return name;
    }
}
