package com.example.trial.Model;

public class Root {
    private Collection collection;

    public Root(Collection collection) {
        this.collection = collection;
    }

    public Root() {
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
