package aiss.bitbucketminer.model.Corrección;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public class GitMinerCommit {

    private String  id;

    private String title;

    private String message;

    private String authorname;

    private String author_email;

    private LocalDateTime authored_date;

    private String web_url;



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

}
