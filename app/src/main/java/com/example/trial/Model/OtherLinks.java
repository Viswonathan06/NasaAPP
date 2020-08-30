package com.example.trial.Model;

public class OtherLinks {
    private String prompt;
    private String href;
    private String rel;

    public OtherLinks() {
    }

    public OtherLinks(String prompt, String href, String rel) {
        this.prompt = prompt;
        this.href = href;
        this.rel = rel;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}

