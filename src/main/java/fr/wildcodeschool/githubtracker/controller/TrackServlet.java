package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.dao.GithubUtils;
import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrackServlet", urlPatterns = {"/track"})
public class TrackServlet extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger("MyLog");
    // Demande au serveur d'appli de créer GithubersService pour nous
    @Inject
    private @InMemory GithuberDAO memGithuber;
    @Inject
    private GithubUtils gutil;  //Inject obligé en private sinon pb...


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqLog= (String) request.getParameter("login");   // En post on recois des parametres... En get on envoie des attributs
        slf4jLogger.info("Get "+ reqLog+" parameter");
        memGithuber.saveGithuber(gutil.parseGithuber(reqLog));
        response.sendRedirect("githubers");  // TODO ask Fabien "githuber vs /tracklogform"
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/tracklogform.jsp").forward(request,response);
    }
}
