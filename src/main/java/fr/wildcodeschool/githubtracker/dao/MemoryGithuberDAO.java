package fr.wildcodeschool.githubtracker.dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.controller.GithubersServlet;
import fr.wildcodeschool.githubtracker.model.Githuber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Dependent // Maintient cette classe tant que GithubersService en a besoin
@ApplicationScoped
public class MemoryGithuberDAO implements GithuberDAO {

    private Map<String, Githuber> gitHuberMap=null; //new HashMap<>();
    private final Logger slf4jLogger = LoggerFactory.getLogger(MemoryGithuberDAO.class);
    static final String GITURL="https://api.github.com/users/";

    @Inject private ObjectMapper om;
    public MemoryGithuberDAO() {
        gitHuberMap=new HashMap<>();
    }


    @PostConstruct
    private void postConstructFonction() {

            slf4jLogger.info("PostConstruct called");
            String[] logs={"EddyAtPessac","veropichon","smelldat"};
            for (String log : logs ) {
                gitHuberMap.put(log, parseGithuber(log));
            }
    }

    public Githuber parseGithuber(String login)  {

        String url=GITURL+ login;
        Githuber mygit = null;
        try {
            mygit = om.readValue( new URL(url), Githuber.class);
            slf4jLogger.info("Retreive of"+login+ "ok");
        } catch (Exception e) {
            e.printStackTrace();
            slf4jLogger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        /*
        catch (JsonMappingException e) {
            e.printStackTrace();
            slf4jLogger.error(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            slf4jLogger.error(e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            slf4jLogger.error(e.getMessage());
        } catch (IOException e) {
            slf4jLogger.error(e.getMessage());
            e.printStackTrace();
        }
        */
      return(mygit);

    }



    @Override
    public List<Githuber> getGithubers() {
        List <Githuber> myList=new ArrayList<>(gitHuberMap.values());
        return myList;
    }


    @Override
    public void saveGithuber(Githuber githuber) {

        gitHuberMap.put(githuber.getLogin(), parseGithuber(githuber.getLogin()));
        ;
    }
}
