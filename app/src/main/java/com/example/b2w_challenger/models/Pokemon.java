package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("abilities")
    List<Abilities> abilities = null;

    @SerializedName("sprites")
    List<Sprites> sprites = null;

    @SerializedName("stats")
    List<Stats> stats = null;

    @SerializedName("types")
    List<Types> types = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public List<Sprites> getSprites() {
        return sprites;
    }

    public void setSprites(List<Sprites> sprites) {
        this.sprites = sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }
}