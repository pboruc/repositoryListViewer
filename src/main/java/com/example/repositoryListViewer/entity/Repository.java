package com.example.repositoryListViewer.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Repository {
    private String RepositoryName;
    private String OwnerLogin;
    private List<Branch> branches;

    public Repository() {
    }

    public String getRepositoryName() {
        return RepositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        RepositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return OwnerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        OwnerLogin = ownerLogin;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
