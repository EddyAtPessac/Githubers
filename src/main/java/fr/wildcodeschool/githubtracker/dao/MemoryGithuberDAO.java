package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Dependent // Maintient cette classe tant que GithubersService en a besoin
@ApplicationScoped
@InMemory
public class MemoryGithuberDAO implements GithuberDAO {

    private Map<String, Githuber> gitHuberMap=null; //new HashMap<>();
    private final Logger slf4jLogger = LoggerFactory.getLogger(MemoryGithuberDAO.class);
    static final String GITURL="https://api.github.com/users/";

    //@Inject private ObjectMapper om;
    public MemoryGithuberDAO() {
        gitHuberMap=new HashMap<>();
    }
    @Inject
    private GithubUtils gutil;  //Inject obligé en private sinon pb...

    @PostConstruct
    private void postConstructFonction() {

            slf4jLogger.info("PostConstruct called");
            String[] logs={"EddyAtPessac","veropichon","smelldat"};
            for (String log : logs ) {
                gitHuberMap.put(log, gutil.parseGithuber(log));
            }
    }




    @Override
    public List<Githuber> getGithubers() {
        List <Githuber> myList=new ArrayList<>(gitHuberMap.values());
        return myList;
    }


    @Override
    public void saveGithuber(Githuber githuber) {

        gitHuberMap.put(githuber.getLogin(), gutil.parseGithuber(githuber.getLogin()));
        ;
    }
}