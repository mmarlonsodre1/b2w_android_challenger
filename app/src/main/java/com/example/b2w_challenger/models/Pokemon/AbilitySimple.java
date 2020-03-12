package com.example.b2w_challenger.models.Pokemon;

import com.google.gson.annotations.SerializedName;

public class AbilitySimple {
    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

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