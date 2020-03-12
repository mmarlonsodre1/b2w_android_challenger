package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class StatsSimple {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
