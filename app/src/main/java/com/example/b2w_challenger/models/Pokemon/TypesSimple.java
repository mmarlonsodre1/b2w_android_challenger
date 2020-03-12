package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class TypesSimple {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
