package aiss.bitbucketminer.model.Corrección;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "issues")
public class GitMinerIssues {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private String state;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "closed_at")
    private LocalDateTime closed_at;

    @ElementCollection
    @CollectionTable(name = "issue_labels", joinColumns = @JoinColumn(name = "issue_id"))
    @Column(name = "label")
    private List<String> labels;

    @Column(name = "votes")
    private Integer votes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "issue_id")
    private List<GitMinerComment> gitMinerComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private GitMinerProject gitMinerProject;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", nullable = false)
    private GitMinerUser gitMinerUser;


    // Constructor vacío
    public GitMinerIssues() {
        this.gitMinerComments = new ArrayList<>();
    }

    // Constructor con parámetros
    public GitMinerIssues(String id, String title, String description, String state, LocalDateTime created_at,
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
        this.gitMinerComments = new ArrayList<>();
        this.gitMinerProject= new GitMinerProject();
        this.gitMinerUser= new GitMinerUser();
    }

    public List<GitMinerComment> getGitMinerComments() {
        return gitMinerComments;
    }

    public void setGitMinerComments(List<GitMinerComment> gitMinerComments) {
        this.gitMinerComments = gitMinerComments;
    }

    public GitMinerProject getGitMinerProject() {
        return gitMinerProject;
    }

    public void setGitMinerProject(GitMinerProject gitMinerProject) {
        this.gitMinerProject = gitMinerProject;
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

    public List<GitMinerComment> getComments() {
        return gitMinerComments;
    }

    public void setComments(List<GitMinerComment> gitMinerComments) {
        this.gitMinerComments = gitMinerComments;
    }

    public GitMinerProject getProject() {
        return gitMinerProject;
    }

    public void setProject(GitMinerProject gitMinerProject) {
        this.gitMinerProject = gitMinerProject;
    }
}