package fr.wildcodeschool.githubtracker.bdd;
import java.sql.*;

public class DbConnectionFactory {

    static final String url = "jdbc:mysql://localhost:3306/githubtracker?serverTimezone=GMT";

    public Connection openConnection()  {
            try {
                Class.forName( "com.mysql.jdbc.Driver" );

            } catch ( ClassNotFoundException e ) {

            }


            String utilisateur = "root";
            String motDePasse = "Eddy2222";
            Connection connexion = null;
            try {
                connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return connexion;
        }

        public void closeConnection(ResultSet resultat, Statement statement, Connection connexion) {

            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }

        }
}
