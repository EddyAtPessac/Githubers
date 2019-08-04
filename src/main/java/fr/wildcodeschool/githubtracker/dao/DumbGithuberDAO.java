package fr.wildcodeschool.githubtracker.dao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Dependent // Maintient cette classe tant que GithubersService en a besoin
public class DumbGithuberDAO /*implements GithuberDAO*/{

    public DumbGithuberDAO() {
    }

    //@Override
    public List<Githuber> getGithubers() {
        ArrayList<Githuber> githubers = new ArrayList<>();
        githubers.add(new Githuber("Eddy", "eddy@gmail", "edylog", "edyId", "https://myavatar.com?img=098"));
        githubers.add(new Githuber("Valentin", "valentin33@gmail", "vallog", "ValId", "https://myavatar.com?img=090"));
        githubers.add(new Githuber("Alex", "alex@gmail", "alexlog", "AlexId", "https://myavatar.com?img=345"));
        githubers.add(new Githuber("Yannis", "yannis@orange.fr", "yalog", "yaId", "https://myavatar.com?img=1235"));
        githubers.add(new Githuber("Edwin", "edwin33@gmail", "Edwinlog", "EdwinId", "https://myavatar.com?img=12"));

        //return Collections.unmodifiableList(githubers); // Read-only list !
        return githubers;
    }

    public void saveGithuber(Githuber githuber) {
        throw (new UnsupportedOperationException());
    }
}
