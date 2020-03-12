package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("abilities")
    private List<Ability> abilities = null;

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("stats")
    private List<Stats> stats = null;

    @SerializedName("types")
    private List<Types> types = null;

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

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
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