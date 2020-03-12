package com.example.b2w_challenger.models.Evolution;

import com.google.gson.annotations.SerializedName;

public class Evolution {
    @SerializedName("chain")
    private EvolvesTo evolvesTo;

    @SerializedName("id")
    private int id;

    public EvolvesTo getEvolvesTo() {
        return evolvesTo;
    }

    public int getId() {
        return id;
    }
}
