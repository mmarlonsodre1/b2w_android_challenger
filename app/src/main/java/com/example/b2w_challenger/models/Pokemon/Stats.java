package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("base_stat")
    String base_stat;

    @SerializedName("stat")
    StatsSimple stat;

    public String getBase_stat() {
        return base_stat;
    }

    public StatsSimple getStat() {
        return stat;
    }
}