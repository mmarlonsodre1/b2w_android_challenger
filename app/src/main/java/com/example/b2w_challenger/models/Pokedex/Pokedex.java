package com.example.b2w_challenger.models.Pokedex;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokedex {
    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String next;

    @SerializedName("results")
    private List<PokemonSimple> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<PokemonSimple> getResults() {
        return results;
    }

    public void setResults(List<PokemonSimple> results) {
        this.results = results;
    }
}