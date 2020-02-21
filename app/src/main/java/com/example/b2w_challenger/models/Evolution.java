package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Evolution {
    @SerializedName("chain")
    EvolvesTo evolves_to;

    @SerializedName("id")
    int id;

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

    public class EvolutionInfo {
        @SerializedName("name")
        String name;

        public String getName() {
            return name;
        }
    }

    public EvolvesTo getEvolves_to() {
        return evolves_to;
    }

    public int getId() {
        return id;
    }
}
