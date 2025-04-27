package aiss.bitbucketminer.model.Corrección;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

@Entity
@Table(name="commits")
public class GitMinerCommit {

    @Id
    private String  id;

    @Column(name="title")
    @NotEmpty(message = "El nombre del commit no debe estar vacío")
    private String title;

    @Column(name="message")
    @NotNull(message = "El mensaje no puede ser nulo")
    private String message;

    @Column(name="author_name")
    private String authorname;

    @Column(name="author_email")
    @Email
    private String author_email;

    @Column(name="authored_date")
    @Past
    private LocalDateTime authored_date;

    @Column(name="web_url")
    private String web_url;

    private String gitMinerProject;


    public GitMinerCommit() {
    }

    public GitMinerCommit(String title, String message, String authorname, String author_email, LocalDateTime authored_date, String web_url) {
        this.id= id;
        this.title = title;
        this.message = message;
        this.authorname = author_email;
        this.author_email = author_email;
        this.authored_date = authored_date;
        this.web_url = web_url;
        this.gitMinerProject = gitMinerProject;
    }

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public LocalDateTime getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(LocalDateTime authored_date) {
        this.authored_date = authored_date;
    }

    public String getWeb_url() {
        return web_url;
    }
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getProject() {
        return gitMinerProject;
    }

    public void setProject(String gitMinerProject) {
        this.gitMinerProject = gitMinerProject;
    }
}
