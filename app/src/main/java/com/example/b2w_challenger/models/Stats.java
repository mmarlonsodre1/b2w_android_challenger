package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stats {
    @SerializedName("base_stat")
    String base_stat;

    @SerializedName("stat")
    List<Stat> stat = null;

    private class Stat {
        @SerializedName("name")
        String name;
    }
}