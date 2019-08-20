package fr.wildcodeschool.githubtracker.dao;
import fr.wildcodeschool.githubtracker.model.Githuber;
import java.util.List;

public interface GithuberDAO {
    List<Githuber> getGithubers();
    void saveGithuber(Githuber githuber);
    void deleteGithuber(String login);
}

