package com.example.repositoryListViewer.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class Repository {
    private String RepositoryName;

    private String fullName;
    private Owner Owner;
    private List<Branch> branches;

    public Repository() {
    }

    @JsonGetter("name")
    public String getRepositoryName() {
        return RepositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        RepositoryName = repositoryName;
    }

    @JsonGetter("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonSetter("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return Owner;
    }

    public void setOwner(Owner owner) {
        Owner = owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
