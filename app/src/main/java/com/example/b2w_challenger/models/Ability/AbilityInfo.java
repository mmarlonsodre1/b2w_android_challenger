package com.example.b2w_challenger.models.Ability;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbilityInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("effect_changes")
    private List<EffectChange> effectChanges;

    @SerializedName("effect_entries")
    private List<EffectEntrie> effectEntries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<EffectChange> getEffectChanges() {
        return effectChanges;
    }

    public void setEffectChanges(List<EffectChange> effectChanges) {
        this.effectChanges = effectChanges;
    }

    public List<EffectEntrie> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<EffectEntrie> effectEntries) {
        this.effectEntries = effectEntries;
    }
}
