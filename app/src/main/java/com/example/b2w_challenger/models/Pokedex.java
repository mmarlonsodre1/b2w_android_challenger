package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokedex {
    @SerializedName("count")
    int count;

    @SerializedName("next")
    String next;

    @SerializedName("results")
    List<Pokemon> results = null;

    public Pokedex(List<Pokemon> results) {
        this.results = results;
    }

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

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}