package com.example.trial.Model;

import java.util.List;

public class SearchData {
    private String date_created;
    private String center;
    private List<String> keywords;
    private String nasa_id;
    private String description;
    private String media_type;
    private String title;

    public SearchData() {
    }

    public SearchData(String date_created, String center, List<String> keywords, String nasa_id, String description, String media_type, String title) {
        this.date_created = date_created;
        this.center = center;
        this.keywords = keywords;
        this.nasa_id = nasa_id;
        this.description = description;
        this.media_type = media_type;
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getNasa_id() {
        return nasa_id;
    }

    public void setNasa_id(String nasa_id) {
        this.nasa_id = nasa_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
