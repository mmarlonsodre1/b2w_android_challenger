package com.example.b2w_challenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbilityInfo {
    @SerializedName("name")
    String name;

    @SerializedName("effect_changes")
    List<EffectChange> effect_changes;

    @SerializedName("effect_entries")
    List<EffectEntrie> effect_entries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EffectChange> getEffect_changes() {
        return effect_changes;
    }

    public void setEffect_changes(List<EffectChange> effect_changes) {
        this.effect_changes = effect_changes;
    }

    public List<EffectEntrie> getEffect_entries() {
        return effect_entries;
    }

    public void setEffect_entries(List<EffectEntrie> effect_entries) {
        this.effect_entries = effect_entries;
    }

    public class EffectChange {
        @SerializedName("effect")
        String effect;

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }
    }

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
}
