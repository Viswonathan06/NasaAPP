package com.example.trial.Model;

public class Links {
    private String href;
    private String render;
    private String rel;

    public Links() {
    }

    public Links(String href, String render, String rel) {
        this.href = href;
        this.render = render;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
