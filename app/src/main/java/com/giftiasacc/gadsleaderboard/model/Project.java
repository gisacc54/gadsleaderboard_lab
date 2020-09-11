package com.giftiasacc.gadsleaderboard.model;

public class Project {
    private String firstName;
    private String lastName;
    private String email;
    private String githubProjectUrl;

    public Project(String firstName, String lastName, String email, String githubProjectUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.githubProjectUrl = githubProjectUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubProjectUrl() {
        return githubProjectUrl;
    }

    public void setGithubProjectUrl(String githubProjectUrl) {
        this.githubProjectUrl = githubProjectUrl;
    }
}
