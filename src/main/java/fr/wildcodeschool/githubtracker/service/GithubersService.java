package fr.wildcodeschool.githubtracker.service;

import fr.wildcodeschool.githubtracker.dao.GithubUtils;
import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InMemory;
import fr.wildcodeschool.githubtracker.model.Githuber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;


@Dependent   // Maintient cette classe tant que la servlet en a besoin
public class GithubersService {
    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersService.class);

    private GithuberDAO gitDao;
    // Grace au inject, le serveur d'appli va instancier l'argument (Une interface),
    // donc il cherche ensuite la classe qui implemente cette interface. Comme il y a
    // 2 implementation de cette interface, on a créé l'annotation InMemory pour colorer
    // DumbGithuberDAO que l'on veut utiliser.

    @Inject
    public GithubersService( @InMemory GithuberDAO gitDao) {
        this.gitDao = gitDao;
    }
    @Inject
    private @InMemory GithuberDAO memGithuber;
    @Inject
    private GithubUtils gutil;  //Pour parseGithuber() Inject obligé en private sinon pb...


    public List<Githuber> getAllGithubers() {
        return gitDao.getGithubers() ;
    }

    public void track(String login) {
        memGithuber.saveGithuber(gutil.parseGithuber(login));
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

