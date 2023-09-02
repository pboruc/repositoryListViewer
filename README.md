# RepositoryListViewer
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Usage](#usage)

## General info
This project is api to list github user repositories which are not forks and are public  
	
## Technologies
Project is created with:
* Java 17
* Spring Boot 3
* Maven 3.8.1
* JSON 20230618
	
## Setup
To run this project, build it with dependencies using your IDE and run it.  

## Usage
The api offers an endpoint at address:  
```
http://localhost:8080/api/repos/{USERNAME}
```
You need to use parameter {USERNAME} and  header "Accept: application/json".  
In response you will get a list of repositories of the given user. Repository name, owner login and branch list with it's name and last commit sha.  
