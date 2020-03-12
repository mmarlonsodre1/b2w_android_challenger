package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("ability")
    private AbilitySimple abilitySimple;

    public void setAbilitySimple(AbilitySimple abilitySimple) {
        this.abilitySimple = abilitySimple;
    }

    public AbilitySimple getAbilitySimple() {
        return abilitySimple;
    }
}