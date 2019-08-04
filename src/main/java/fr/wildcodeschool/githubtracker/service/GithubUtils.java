package fr.wildcodeschool.githubtracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.inject.Inject;
import java.net.URL;

// Cette classe permet d'ajouter la methode parseGithuber() aux classes qui implementent GitHuberDAO

public class GithubUtils {

    static final String GITURL="https://api.github.com/users/";
    @Inject
    private ObjectMapper om;

    public Githuber parseGithuber(String login) {

        String url = GITURL + login;
        Githuber mygit = null;
        try {
            mygit = om.readValue(new URL(url), Githuber.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return mygit;
    }
}
