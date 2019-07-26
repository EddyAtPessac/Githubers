# Githubers
<h1>Wild Code Scool - Quete CDI_1</h1>

J'ai ajouté ça au projet initial:</p>
<i>Dans GithubersServlet:</i></p>
<code>
@WebServlet(name = "GithubersServlet", urlPatterns = {"/githubers"})
public class GithubersServlet extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersServlet.class);

    // Demande au serveur d'appli de créer GithubersService pour nous
    @Inject  private GithubersService githuberServObj;
</code>

<i>Dans GithuberService:</i></p>
<code>
@Dependent   // Maintient cette classe tant que la servlet en a besoin
public class GithubersService {
    private final Logger slf4jLogger = LoggerFactory.getLogger(GithubersService.class);


    private GithuberDAO gitDao;

    // Grace au inject, le serveur d'appli va instancier l'argument (Une interface),
    // donc il cherche ensuite la classe qui implemente cette interface. Comme DumbGithuberDAO
    // est la seule qui le fait, elle est créée dans la foulée.


    @Inject
    public GithubersService(GithuberDAO gitDao) {
        this.gitDao = gitDao;
    }
</code>    

  Plus les @Depends
  <h1>Et ça marche encore !<h1>
