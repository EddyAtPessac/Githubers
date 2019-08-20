package fr.wildcodeschool.githubtracker.dao;
import fr.wildcodeschool.githubtracker.bdd.DbConnectionFactory;
import fr.wildcodeschool.githubtracker.model.Githuber;
import fr.wildcodeschool.githubtracker.service.GithubUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Dependent // Maintient cette classe tant que GithubersService en a besoin
@ApplicationScoped
@InDatabase
public class DBGithuberDAO implements GithuberDAO  {

    @Resource(lookup = "java:/jdbc/myDB")
    private DataSource myDB;
    //fr.wildcodeschool.githubtracker.dao/DBGithuberDAO/myDB
    private Map<String, Githuber> gitHuberMap=null; //new HashMap<>();
    private final Logger slf4jLogger = LoggerFactory.getLogger(fr.wildcodeschool.githubtracker.dao.MemoryGithuberDAO.class);
    static final String GITURL="https://api.github.com/users/";
    PreparedStatement pStatement = null;
    ResultSet resultat = null;

    @Inject
    private GithubUtils gutil;  //Inject obligé en private sinon pb...

    private Connection getCnx() {
        Connection cnx=null;
        try {
            cnx = myDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnx;
    }

    @Override
    public List<Githuber> getGithubers() {
        String strGetGithubers= "select `id`,`name`,`email`,`login`, `github_id`, `avatar_url` from `githuber` order by github_id;";
        List <Githuber> myList=new ArrayList<>();
        //DbConnectionFactory dbFactory= new DbConnectionFactory();  // Still used to close statement and resultat
        Connection cnx = getCnx();
         try {
            if (cnx != null) {
                pStatement = (PreparedStatement) cnx.prepareStatement(strGetGithubers);
                resultat = pStatement.executeQuery();
                Githuber git=null;
                while (resultat != null && resultat.next()) {
                    git = new Githuber(
                            resultat.getString("name"), resultat.getString("email"),
                            resultat.getString("login"), resultat.getString("github_id"),
                            resultat.getString("avatar_url" )
                    );
                    //git.setId(resultat.getLong("id"));   // rajoute l'id de la BD
                    myList.add(git);
                } // while
                if (pStatement != null) pStatement.close();
                if (resultat != null) resultat.close();
            } // if cnx
        } catch (SQLException e) {
            e.printStackTrace();
        }
       //dbFactory.closeConnection(resultat,pStatement,null);
       return myList;
    }

    public void deleteGithuber(String gitlog) {

            String strGetId="SELECT id FROM `githuber` WHERE login = ?;";
            String strKillGit="DELETE FROM `githuber` WHERE `id` = ";
            Long id=null;
            //DbConnectionFactory dbFactory= new DbConnectionFactory();
            //Connection cnx = dbFactory.openConnection();
            Connection cnx = getCnx();
            try {
                if (cnx != null) {
                    // Recup of id by gitId
                    pStatement = (PreparedStatement) cnx.prepareStatement(strGetId);
                    pStatement.setString(1,gitlog);
                    resultat = pStatement.executeQuery();
                    if (resultat.next()) {
                        id = resultat.getLong("id");
                    }
                    // Delete by id
                    pStatement = (PreparedStatement) cnx.prepareStatement(strKillGit+ id +";");
                    //pStatement.setInt(1, id);
                    pStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ;//dbFactory.closeConnection(null,pStatement,cnx);
            }
        }

        @Override
        public void saveGithuber(Githuber git) {
            String strSetGithubers= "insert into `githuber` (`name`,`email`,`login`, `github_id`, `avatar_url`)"
                                    +" VALUES (?,?,?,?,?);";
            //DbConnectionFactory dbFactory= new DbConnectionFactory();
            //Connection cnx = dbFactory.openConnection();
            Connection cnx = getCnx();
            try {
                if (cnx != null) {
                    pStatement = (PreparedStatement) cnx.prepareStatement(strSetGithubers);
                    pStatement.setString(1,git.getName());
                    pStatement.setString(2,git.getEmail());
                    pStatement.setString(3,git.getLogin());
                    pStatement.setString(4,git.getgitId());
                    pStatement.setString(5,git.getAvatarUrl());
                    pStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //dbFactory.closeConnection(null,pStatement,cnx);
            }
        }
}
