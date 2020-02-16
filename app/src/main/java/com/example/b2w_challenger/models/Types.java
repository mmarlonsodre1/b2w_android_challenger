package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Types {
    @SerializedName("type")
    List<Type> abilities = null;

    public List<Type> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Type> abilities) {
        this.abilities = abilities;
    }

    private class Type {
        @SerializedName("name")
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}