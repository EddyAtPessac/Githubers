package fr.wildcodeschool.githubtracker.dao;
import fr.wildcodeschool.githubtracker.model.Githuber;
import fr.wildcodeschool.githubtracker.service.GithubUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@ApplicationScoped
@Jpa
public class JpaGithuberDao  implements GithuberDAO {

    @PersistenceContext(unitName = "GitPersist")
    private EntityManager em;

    private Map<String, Githuber> gitHuberMap=null; //new HashMap<>();
    private final Logger log = LoggerFactory.getLogger("JpaGithuber");
    @Inject
    private GithubUtils gutil;  //Inject obligé en private sinon pb...

    public List<Githuber> getGithubers() {
        List<Githuber> myList = (List<Githuber>) em.createQuery( " select git from Githuber git" , Githuber.class)
        .getResultList();
        return (myList);
    }

    public void deleteGithuber(String login) {
        Query findFromLog = em.createQuery("select git from Githuber git where git.login=\'"+login+"\'");
        log.info("Githuber "+login+" remove request");
        Githuber git= (Githuber) findFromLog.getSingleResult();
        if (git != null) {
            em.remove(git);
            log.info("Githuber "+login+" removed");
        }
        else {
            log.error("Githuber login "+login+" not found");
        }
/*
        List<Githuber> myList = (List<Githuber>) findFromLog.getResultList();
        log.info("List info "+myList.size()+" elements");
        for (Githuber git: myList ) {
            log.info("Find "+ git.getLogin()+".");
        }
*/
     }


    public void saveGithuber(Githuber githuber) {
        log.info("Add git login: "+ githuber.getLogin()  + " Git id:"+githuber.getgitId()+".");
         try {
            em.persist(githuber);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
/*
        Query findlastId = em.createQuery("select git from Githuber git ", Githuber.class);
        List<Githuber> myList = (List<Githuber>) findlastId.getResultList();
        for (Githuber gt : myList ) {
            log.info("log: "+gt.getLogin()+" "+gt.getId());
        };
*/
   }

}
