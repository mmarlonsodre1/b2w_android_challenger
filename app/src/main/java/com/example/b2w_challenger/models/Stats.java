package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("base_stat")
    String base_stat;

    @SerializedName("stat")
    Stat stat;

    public class Stat {
        @SerializedName("name")
        String name;
    }
}