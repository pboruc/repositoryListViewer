package com.example.repositoryListViewer.service;

import com.example.repositoryListViewer.entity.Branch;
import com.example.repositoryListViewer.entity.Repository;
import com.example.repositoryListViewer.exception.RepositoryNameNotFoundException;
import com.example.repositoryListViewer.exception.UsernameNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
            reposJson = objectMapper.writeValueAsString(setRepositoryData(downloadReposForUser(username)));
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return reposJson;
    }

    private JSONArray downloadReposForUser(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.github.com/users/" + username + "/repos";
        String gitHubResponse = "";
        try {
            gitHubResponse = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException ex) {
            throw new UsernameNotFoundException("Username not found " + username);
        }
        return new JSONArray(gitHubResponse);
    }

    private List<Repository> setRepositoryData(JSONArray jsonArray) {
        List<Repository> repositories = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = ((JSONObject) object);
            if (!jsonObject.getBoolean("fork")) {
                Repository repository = new Repository();
                repository.setRepositoryName(jsonObject.getString("name"));
                repository.setOwnerLogin(jsonObject.getJSONObject("owner").getString("login"));
                repository.setBranches(setBranchesData(downloadBranchesForRepos(jsonObject.getString("full_name"))));
                repositories.add(repository);
            }
        }
        return repositories;
    }

    private JSONArray downloadBranchesForRepos(String repoFullName) {
        String url = "https://api.github.com/repos/" + repoFullName + "/branches";
        RestTemplate restTemplate = new RestTemplate();
        String gitHubResponse = "";
        try {
            gitHubResponse = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException ex) {
            throw new RepositoryNameNotFoundException("Repository full name not found " + repoFullName);
        }
        return new JSONArray(gitHubResponse);
    }

    private List<Branch> setBranchesData(JSONArray jsonArray) {
        List<Branch> branches = new ArrayList<>();
        for (Object object : jsonArray) {
            Branch branch = new Branch();
            JSONObject jsonObject = ((JSONObject) object);
            branch.setName(jsonObject.getString("name"));
            branch.setLastCommitSha(jsonObject.getJSONObject("commit").getString("sha"));
            branches.add(branch);
        }
        return branches;
    }
}
