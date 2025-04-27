package aiss.bitbucketminer.model.Corrección;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class GitMinerUser {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "avatar_url")
    private String avatar_url;

    @Column(name = "web_url")
    private String web_url;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<GitMinerIssues> issues;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<GitMinerComment> gitMinerComments;

    public GitMinerUser() {
    }

    public GitMinerUser(String id, String username, String name,
                        String state, String avatar_url, String web_url) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.state = state;
        this.avatar_url = avatar_url;
        this.web_url = web_url;
        this.issues = new ArrayList<>();
        this.gitMinerComments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<GitMinerIssues> getIssues() {
        return issues;
    }

    public void setIssues(List<GitMinerIssues> issues) {
        this.issues = issues;
    }

    public List<GitMinerComment> getComments() {
        return gitMinerComments;
    }

    public void setComments(List<GitMinerComment> gitMinerComments) {
        this.gitMinerComments = gitMinerComments;
    }
}
