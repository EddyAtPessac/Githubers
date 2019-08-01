package fr.wildcodeschool.githubtracker.service;

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
    // donc il cherche ensuite la classe qui implemente cette interface. Comme DumbGithuberDAO
    // est la seule qui le fait, elle est créée dans la foulée.
    @Inject
    public GithubersService( @InMemory GithuberDAO gitDao) {
        this.gitDao = gitDao;
    }

    /*  Remplacé par le contructeur avec inject


        public GithubersService() {
            this.gitDao=new DumbGithuberDAO();
        }

   }


    */
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

