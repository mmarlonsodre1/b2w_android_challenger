package com.example.b2w_challenger.models.Ability;

import com.google.gson.annotations.SerializedName;

public class EffectChange {
    @SerializedName("effect")
    private String effect;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
