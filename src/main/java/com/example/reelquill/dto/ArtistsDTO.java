package com.example.reelquill.dto;

import java.util.List;

public class ArtistsDTO {
    private List<String> primary;
    private List<String> featured;
    private List<String> all;

    public List<String> getPrimary() {
        return primary;
    }

    public void setPrimary(List<String> primary) {
        this.primary = primary;
    }

    public List<String> getFeatured() {
        return featured;
    }

    public void setFeatured(List<String> featured) {
        this.featured = featured;
    }

    public List<String> getAll() {
        return all;
    }

    public void setAll(List<String> all) {
        this.all = all;
    }
}

