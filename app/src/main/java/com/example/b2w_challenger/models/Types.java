package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

public class Types {
    @SerializedName("type")
    Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public class Type {
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