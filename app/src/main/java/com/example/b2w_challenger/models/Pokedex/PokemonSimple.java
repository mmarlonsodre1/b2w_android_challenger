package com.example.b2w_challenger.models.Pokedex;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PokemonSimple implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    private Integer id;

    public PokemonSimple(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

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

    public Integer getId() {
        return id;
    }
}