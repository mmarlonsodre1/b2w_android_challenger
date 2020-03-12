package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("base_stat")
    private String baseStat;

    @SerializedName("stat")
    private StatsSimple stat;

    public String getBaseStat() {
        return baseStat;
    }

    public StatsSimple getStat() {
        return stat;
    }
}