package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.service.GithubersService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UntrackServlet",  urlPatterns = {"/gitkiller"})
public class UntrackServlet extends HttpServlet {

    @Inject private GithubersService gitserv;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String gitlog=request.getParameter("gitLog");
    gitserv.unTrack(gitlog);
    response.sendRedirect("githubers");
    }
}
