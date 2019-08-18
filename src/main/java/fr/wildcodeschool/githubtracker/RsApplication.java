package fr.wildcodeschool.githubtracker;

import fr.wildcodeschool.githubtracker.service.GithubersService;
import fr.wildcodeschool.githubtracker.service.RsGithubersService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(RsGithubersService.class);
        return classes;
    }
}
