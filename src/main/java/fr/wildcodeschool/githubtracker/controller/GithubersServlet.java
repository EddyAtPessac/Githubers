package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "GithubersServlet", urlPatterns = {"/githubers"})
//@WebServlet(name = "GithubersServlet")
public class GithubersServlet extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Githuber> githubers = new ArrayList<>();
        githubers.add(new Githuber("Eddy","eddy@gmail", "edylog", "edyId", "https://myavatar.com?img=098"));
        githubers.add(new Githuber("Valentin","valentin33@gmail", "vallog", "ValId", "https://myavatar.com?img=090"));
        githubers.add(new Githuber("Alex","alex@gmail", "alexlog", "AlexId", "https://myavatar.com?img=345"));
        githubers.add(new Githuber("Yannis","yannis@orange.fr", "yalog", "yaId", "https://myavatar.com?img=1235"));
        githubers.add(new Githuber("Edwin","edwin33@gmail", "Edwinlog", "EdwinId", "https://myavatar.com?img=12"));
        request.setAttribute("gitlist",githubers);
        slf4jLogger.info(githubers.get(2).getName().toString());
        request.getRequestDispatcher("/githubers.jsp").forward(request,response);
    }
}