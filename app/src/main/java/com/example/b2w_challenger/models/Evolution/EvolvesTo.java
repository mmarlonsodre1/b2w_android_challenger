package com.example.b2w_challenger.models.Evolution;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EvolvesTo{
    @SerializedName("evolves_to")
    private List<EvolvesTo> evolvesTo;

    @SerializedName("species")
    private EvolutionInfo evolutionInfo;

    public List<EvolvesTo> getEvolvesTo() {
        return evolvesTo;
    }

    public EvolutionInfo getEvolutionInfo() {
        return evolutionInfo;
    }
}