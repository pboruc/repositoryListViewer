package com.example.repositoryListViewer.exception;

public class RepositoryNameNotFoundException extends RuntimeException {

    public RepositoryNameNotFoundException(String message) {
        super(message);
    }

    public RepositoryNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryNameNotFoundException(Throwable cause) {
        super(cause);
    }
}
