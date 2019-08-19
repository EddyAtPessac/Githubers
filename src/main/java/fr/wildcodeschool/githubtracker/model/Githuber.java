package fr.wildcodeschool.githubtracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="githuber")

public class Githuber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name="github_id")
    private String gitId;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="login")
    private String login;
    @Column(name="avatar_url")
    private String avatarUrl;


    @JsonCreator
    public Githuber(@JsonProperty("name") String name, @JsonProperty("email")String email,
                    @JsonProperty("login") String login, @JsonProperty("id") String gitId,
                    @JsonProperty("avatar_url")String avatarUrl) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.gitId = gitId;
        this.avatarUrl = avatarUrl;
    }

    public Githuber() {
    }

    public Long getId() {   return id;   }

    public void setId(Long id) { this.id = id;   }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getgitId() {
        return gitId;
    }

    public void setgitId(String id) {
        this.gitId = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

