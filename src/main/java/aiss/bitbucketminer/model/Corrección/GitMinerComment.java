package aiss.bitbucketminer.model.Corrección;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Comments")

public class GitMinerComment {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "body")
    private String body;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private GitMinerIssues gitMinerIssue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private GitMinerUser gitMinerUser;



    public GitMinerComment() {
    }

    public GitMinerComment(String id, String body,
                           LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.body = body;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.gitMinerIssue = gitMinerIssue;
        this.gitMinerUser = gitMinerUser;

    }

    public GitMinerIssues getGitMinerIssue() {
        return gitMinerIssue;
    }

    public void setGitMinerIssue(GitMinerIssues gitMinerIssue) {
        this.gitMinerIssue = gitMinerIssue;
    }

    public GitMinerUser getGitMinerUser() {
        return gitMinerUser;
    }

    public void setGitMinerUser(GitMinerUser gitMinerUser) {
        this.gitMinerUser = gitMinerUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
