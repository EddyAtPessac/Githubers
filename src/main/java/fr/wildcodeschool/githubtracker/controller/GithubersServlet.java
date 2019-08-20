package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.dao.DumbGithuberDAO;
import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.githubtracker.service.GithubersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet(name = "GithubersServlet", urlPatterns = {"/githubers"})
public class GithubersServlet extends HttpServlet {

    //private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersServlet.class);
    private final Logger slf4jLogger = LoggerFactory.getLogger("MyLog");
    // Demande au serveur d'appli de créer GithubersService pour nous
    @Inject  private GithubersService githuberServObj;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        slf4jLogger.info("************* Do get servlet ***************");
        for (Githuber gt : githuberServObj.getAllGithubers() ) {
            //slf4jLogger.info(gt.getName());
        };
        request.setAttribute("gitlist", githuberServObj.getAllGithubers());
            //slf4jLogger.info(githuberServObj.getAllGithubers().get(2).getName().toString());
        //Githuber toto= githuberServObj.getGithuber("edylog");
        //String name= (toto!=null) ? toto.getName().toString() : "Not found";
        //

        request.getRequestDispatcher("/githubers.jsp").forward(request,response);
    }
}