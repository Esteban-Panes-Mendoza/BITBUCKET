package aiss.bitbucketminer.model.Corrección;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class GitMinerProject {

    private String id;

    private String name;

    private String web_url;

    private List<GitMinerCommit> Commits= new ArrayList<>();

    private List<GitMinerIssues> issues;

    public GitMinerProject() {

    }

    public GitMinerProject(String name, String web_url) {
        this.name = name;
        this.web_url = web_url;
        this.issues = new ArrayList<>();
        this.Commits = new ArrayList<>();
    }

    public void addCommit(GitMinerCommit commit) {
        Commits.add(commit);
    }
    public List<GitMinerCommit> Commits() {
        return Commits;
    }

    public void setGitMinerCommits(List<GitMinerCommit> Commits) {
        this.Commits = Commits;
    }

    public void addIssue(GitMinerIssues issue) {
        issues.add(issue);
        issue.setProject(this);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<GitMinerCommit> getCommits() {
        return Commits;
    }

    public void setCommits(List<GitMinerCommit> gitMinerCommits) {
        this.Commits = gitMinerCommits;
    }

    public List<GitMinerIssues> getIssues() {
        return issues;
    }

    public void setIssues(List<GitMinerIssues> issues) {
        this.issues = issues;

    }
}
