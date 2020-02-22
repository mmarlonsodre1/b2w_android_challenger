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

    public static class PokemonSimple implements Serializable {
        public PokemonSimple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @SerializedName("name")
        String name;

        @SerializedName("url")
        String url;

        Integer id;

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

        public int getId() {
            return id;
        }
    }
}