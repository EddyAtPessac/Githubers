package fr.wildcodeschool.githubtracker.service;

import fr.wildcodeschool.githubtracker.dao.DumbGithuberDAO;
import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.model.Githuber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GithubersService {
    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersService.class);
    GithuberDAO gitDao;

    public GithubersService(GithuberDAO gitDao) {
        this.gitDao = gitDao;
    }

    public GithubersService() {
        this.gitDao=new DumbGithuberDAO();
    }

    public List<Githuber> getAllGithubers() {
        return gitDao.getGithubers() ;
    }

    public void track(String login) {
        // TODO: track githuber
    }

    public Githuber getGithuber(String login) {
        Githuber myGit=null;
        Stream<Githuber>  myGitStream= gitDao.getGithubers().stream();

/*
        try {
            myGit = (Githuber) myGitStream.filter(gh -> gh.getLogin().equals(login))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            slf4jLogger.info("login %s not found");
            e.printStackTrace();
        }
*/
        myGit = (Githuber) myGitStream.filter(gh -> gh.getLogin().equals(login))
                .findAny()
                .orElse(null);

        //collect(Collectors.toSet());

        return myGit;

    }
}

