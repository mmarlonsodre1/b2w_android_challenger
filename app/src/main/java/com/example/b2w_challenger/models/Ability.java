package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("ability")
    AbilitySimple abilitySimple;

    public void setAbilitySimple(AbilitySimple abilitySimple) {
        this.abilitySimple = abilitySimple;
    }

    public AbilitySimple getAbilitySimple() {
        return abilitySimple;
    }

    public class AbilitySimple {
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