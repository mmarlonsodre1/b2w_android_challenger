package com.example.b2w_challenger.models.Evolution;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EvolvesTo{
    @SerializedName("evolves_to")
    List<EvolvesTo> evolves_to;

    @SerializedName("species")
    EvolutionInfo evolutionInfo;

    public List<EvolvesTo> getEvolves_to() {
        return evolves_to;
    }

    public EvolutionInfo getEvolutionInfo() {
        return evolutionInfo;
    }
}