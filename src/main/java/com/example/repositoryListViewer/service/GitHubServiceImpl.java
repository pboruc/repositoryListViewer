package com.example.repositoryListViewer.service;

import com.example.repositoryListViewer.entity.Branch;
import com.example.repositoryListViewer.entity.Repository;
import com.example.repositoryListViewer.exception.RepositoryNameNotFoundException;
import com.example.repositoryListViewer.exception.UsernameNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class GitHubServiceImpl implements GitHubService {

    public GitHubServiceImpl() {
    }

    @Override
    public String getReposForUser(String username) {
        ObjectMapper objectMapper = new ObjectMapper();
        String reposJson = "";
        try {
            reposJson = objectMapper.writeValueAsString(downloadReposForUser(username));
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return reposJson;
    }

    private List<Repository> downloadReposForUser(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.github.com/users/" + username + "/repos";
        Repository[] repositories;
        try {
            repositories = restTemplate.getForObject(url, Repository[].class);
            Arrays.stream(repositories).forEach(repository -> repository.setBranches(downloadBranchesForRepos(repository.getFullName())));
        } catch (HttpClientErrorException ex) {
            throw new UsernameNotFoundException("Username not found " + username);
        }
        return Arrays.stream(repositories).toList();
    }

    private List<Branch> downloadBranchesForRepos(String repoFullName) {
        String url = "https://api.github.com/repos/" + repoFullName + "/branches";
        Branch[] branches;
        var restTemplate = new RestTemplate();
        try {
            branches = restTemplate.getForObject(url, Branch[].class);
        } catch (HttpClientErrorException ex) {
            throw new RepositoryNameNotFoundException("Repository full name not found " + repoFullName);
        }
        return Arrays.stream(branches).toList();
    }
}
