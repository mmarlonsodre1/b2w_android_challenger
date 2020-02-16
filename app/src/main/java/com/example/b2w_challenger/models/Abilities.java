package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Abilities {
    @SerializedName("ability")
    List<Ability> abilitiy = null;

    public List<Ability> getAbilitiy() {
        return abilitiy;
    }

    public void setAbilitiy(List<Ability> abilitiy) {
        this.abilitiy = abilitiy;
    }

    private class Ability {
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