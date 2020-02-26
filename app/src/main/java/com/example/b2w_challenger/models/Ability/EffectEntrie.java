package com.example.b2w_challenger.models.Ability;

import com.google.gson.annotations.SerializedName;

public class EffectEntrie {
    @SerializedName("effect")
    String effect;

    @SerializedName("short_effect")
    String short_effect;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShort_effect() {
        return short_effect;
    }

    public void setShort_effect(String short_effect) {
        this.short_effect = short_effect;
    }
}