package fr.wildcodeschool.githubtracker.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/login")
public class AuthenticatorEndpoint {
    private final Logger log = LoggerFactory.getLogger("AuthenticatorEndpoint");
    @Inject
    private SimpleKeyGenerator keyGenerator;
    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON) //eg: { "login": "wilder", "password": "wcs12345" }
    public Response authenticate(Credentials creds) {
        log.info("login/password : {}/{}", creds.getLogin(), creds.getPassword());

        if(true) {  //LET'S ASSUME WE LOG IN HERE
            // Issue a token for the user and return it within the response
            String token = issueToken(creds.getLogin());
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
        }
        else {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String login) {
        String str="Accepted";
      return str;
    }
}
