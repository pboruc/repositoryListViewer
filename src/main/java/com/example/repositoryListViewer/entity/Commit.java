package com.example.repositoryListViewer.entity;

public class Commit {
    private String sha;
    private String url;

    public Commit() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
