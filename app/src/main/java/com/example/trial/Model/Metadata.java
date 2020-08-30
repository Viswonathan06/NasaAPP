package com.example.trial.Model;

public class Metadata {
    private int total_hits;

    public Metadata(int total_hits) {
        this.total_hits = total_hits;
    }

    public Metadata() {
    }

    public int getTotal_hits() {
        return total_hits;
    }

    public void setTotal_hits(int total_hits) {
        this.total_hits = total_hits;
    }
}
