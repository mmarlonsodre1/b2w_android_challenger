package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

public class Specie {
    @SerializedName("evolution_chain")
    EvolutionSpecie evolutionSpecie;

    @SerializedName("name")
    String name;

    public class EvolutionSpecie {
        @SerializedName("url")
        String url;

        public String getUrl() {
            return url;
        }
    }

    public EvolutionSpecie getEvolutionSpecie() {
        return evolutionSpecie;
    }

    public String getName() {
        return name;
    }
}
