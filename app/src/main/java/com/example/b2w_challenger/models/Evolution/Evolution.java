package com.example.b2w_challenger.models.Evolution;

import com.google.gson.annotations.SerializedName;

public class Evolution {
    @SerializedName("chain")
    EvolvesTo evolves_to;

    @SerializedName("id")
    int id;

    public EvolvesTo getEvolves_to() {
        return evolves_to;
    }

    public int getId() {
        return id;
    }
}
