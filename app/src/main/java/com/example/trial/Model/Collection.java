package com.example.trial.Model;

import java.util.List;

public class Collection {
    private Metadata metadata;
    private String href;
    private List<Items> items;
    private String version;
    private List<OtherLinks> otherlinks;

    public Collection() {
    }

    public Collection(Metadata metadata, String href, List<Items> items, String version, List<OtherLinks> otherlinks) {
        this.metadata = metadata;
        this.href = href;
        this.items = items;
        this.version = version;
        this.otherlinks = otherlinks;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<OtherLinks> getOtherlinks() {
        return otherlinks;
    }

    public void setOtherlinks(List<OtherLinks> otherlinks) {
        this.otherlinks = otherlinks;
    }
}
