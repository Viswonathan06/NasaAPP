package com.example.trial.Model;

import java.util.List;

public class Items {
    private String href;
    private List<SearchData> data;
    private List<Links> links;

    public Items() {
    }

    public Items(String href, List<SearchData> data, List<Links> links) {
        this.href = href;
        this.data = data;
        this.links = links;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<SearchData> getData() {
        return data;
    }

    public void setData(List<SearchData> data) {
        this.data = data;
    }

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }
}
