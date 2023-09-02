package com.example.repositoryListViewer.rest;

import com.example.repositoryListViewer.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GitHubRestController {

    private GitHubService gitHubService;

    public GitHubRestController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/repos/{username}", produces = "application/json", consumes = "application/json")
    public String getRepos(@PathVariable String username) {
        return gitHubService.getReposForUser(username);
    }
}
