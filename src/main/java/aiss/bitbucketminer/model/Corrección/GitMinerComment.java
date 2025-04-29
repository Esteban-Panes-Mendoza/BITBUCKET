package aiss.bitbucketminer.model.Corrección;


import jakarta.persistence.*;

import java.time.LocalDateTime;


public class GitMinerComment {

    private Integer id;

    private String body;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private GitMinerUser author;



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

    public GitMinerUser author() {
        return author;
    }

    public void setGitMinerUser(GitMinerUser author) {
        this.author = author;
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

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
