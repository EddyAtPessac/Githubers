package fr.wildcodeschool.githubtracker.service;

import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InDatabase;
import fr.wildcodeschool.githubtracker.dao.Jpa;
import fr.wildcodeschool.githubtracker.model.Githuber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.GET;
import java.util.List;
import java.util.stream.Stream;


@Dependent   // Maintient cette classe tant que la servlet en a besoin
public class GithubersService {
    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersService.class);

    private GithuberDAO gitDao;
    // Grace au inject, le serveur d'appli va instancier l'argument (Une interface),
    // donc il cherche ensuite la classe qui implemente cette interface. Comme il y a
    // 3 implementations de cette interface, on a créé l'annotation InDatabase pour colorer
    // DumbGithuberDAO que l'on veut utiliser.

    @Inject
    public GithubersService( @Jpa GithuberDAO gitDao) {
        this.gitDao = gitDao;
    }
     @Inject
    private GithubUtils gutil;  //Pour parseGithuber() Inject obligé en private sinon pb...


    public List<Githuber> getAllGithubers() {

        return gitDao.getGithubers() ;
    }

    // New git if found, null else
    public Githuber track(String login) {
        Githuber git= gutil.parseGithuber(login);
        if (null != git) {
            gitDao.saveGithuber(git);
        }
        return(git);
    }

    public void unTrack(String login) {
        gitDao.deleteGithuber(login);
/*
        Githuber git= getGithuber(login);
        if (null != git) {
            gitDao.deleteGithuber(git.getgitId());
        }
*/
    }

    public Githuber getGithuber(String login) {
        Githuber myGit=null;
        Stream<Githuber>  myGitStream= gitDao.getGithubers().stream();
        myGit = (Githuber) myGitStream.filter(gh -> gh.getLogin().equals(login))
                .findAny()
                .orElse(null);

        /*
        try {
            myGit = (Githuber) myGitStream.filter(gh -> gh.getLogin().equals(login))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            slf4jLogger.info("login %s not found");
            e.printStackTrace();
        }
*/
        return myGit;
    }
}

