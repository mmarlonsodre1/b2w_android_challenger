package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pokedex {
    @SerializedName("count")
    int count;

    @SerializedName("next")
    String next;

    @SerializedName("results")
    List<PokemonSimple> results = null;

    public Pokedex(List<PokemonSimple> results) {
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

    public List<PokemonSimple> getResults() {
        return results;
    }

    public void setResults(List<PokemonSimple> results) {
        this.results = results;
    }

    public class PokemonSimple implements Serializable {
        @SerializedName("name")
        String name;

        @SerializedName("url")
        String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}