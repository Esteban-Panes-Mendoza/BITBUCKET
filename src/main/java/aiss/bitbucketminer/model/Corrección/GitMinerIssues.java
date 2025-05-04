package aiss.bitbucketminer.model.Corrección;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

public class GitMinerIssues {

    private Integer id;

    private String title;

    private String description;

    private String state;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private LocalDateTime closed_at;

    private List<String> labels;

    private Integer votes;

    private List<GitMinerComment> Comments;

    @JsonIgnore
    private GitMinerProject gitminerproject;

    private GitMinerUser author;


    // Constructor vacío
    public GitMinerIssues() {
        this.Comments = new ArrayList<>();
    }

    // Constructor con parámetros
    public GitMinerIssues(Integer id, String title, String description, String state, LocalDateTime created_at,
                          LocalDateTime updated_at, LocalDateTime closed_at, List<String> labels, Integer votes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.closed_at = closed_at;
        this.labels = labels;
        this.votes = votes;
        this.Comments = new ArrayList<>();
        this.author= new GitMinerUser();
    }

    public List<GitMinerComment> getComments() {
        return Comments;
    }

    public void setComments(List<GitMinerComment> comments) {
        Comments = comments;
    }

    public List<GitMinerComment> Comments() {
        return Comments;
    }

    public void setGitMinerComments(List<GitMinerComment> Comments) {
        this.Comments = Comments;
    }

    public GitMinerUser getAuthor() {
        return author;
    }

    public void setAuthor(GitMinerUser author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(LocalDateTime closed_at) {
        this.closed_at = closed_at;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public void setProject(GitMinerProject gitMinerProject) {
        this.gitminerproject = gitMinerProject;
    }
}