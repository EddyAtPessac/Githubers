package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.model.Githuber;
import fr.wildcodeschool.githubtracker.service.GithubersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@slf4j  // Marche pas
@WebServlet(name = "TrackServlet", urlPatterns = {"/track"})
public class TrackServlet extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger("MyLog");
    @Inject
    GithubersService gitserv;
    // Demande au serveur d'appli de créer GithubersService pour nous

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqLog= (String) request.getParameter("login");   // En post on recois des parametres... En get on envoie des attributs
        slf4jLogger.info("Get "+ reqLog+" parameter");
        Githuber git= gitserv.track(reqLog);
        if (git != null ) {
            response.sendRedirect("githubers");  // Because sendRedirect"githuber vs /tracklogform"
        }
        else {
            request.setAttribute("gitlog",reqLog);  // try to display  "log inconnu"
            request.getRequestDispatcher("/tracklogform.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/tracklogform.jsp").forward(request,response);
    }
}
