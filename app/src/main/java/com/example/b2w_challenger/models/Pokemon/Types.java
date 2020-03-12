package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class Types {
    @SerializedName("type")
    private TypesSimple type;

    public TypesSimple getType() {
        return type;
    }

    public void setType(TypesSimple type) {
        this.type = type;
    }

}