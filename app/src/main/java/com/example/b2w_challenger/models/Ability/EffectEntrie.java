package com.example.b2w_challenger.models.Ability;

import com.google.gson.annotations.SerializedName;

public class EffectEntrie {
    @SerializedName("effect")
    private String effect;

    @SerializedName("short_effect")
    private String shortEffect;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }
}