package aiss.bitbucketminer.model.Corrección;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;


public class GitMinerComment {

    private Integer id;

    private String body;

    private LocalDateTime created_at;

    private Object updated_at;

    private GitMinerUser author;

    @JsonIgnore
    private Integer issueId;


    public GitMinerComment() {
    }

    public GitMinerComment(Integer id, String body,
                           LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.body = body;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;

    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }

    public GitMinerUser getAuthor() {
        return author;
    }

    public void setAuthor(GitMinerUser author) {
        this.author = author;
    }
}
