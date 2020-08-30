package com.example.trial.Model.ClickSearch;

import java.util.List;

public class Collection {
    public List<Item> items;
    public String version;
    public String href;

    public Collection(List<Item> items, String version, String href) {
        this.items = items;
        this.version = version;
        this.href = href;
    }

    public Collection() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
