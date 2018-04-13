package modele;

/**
 *
 * Librairies importées
 */
import java.sql.*;

/**
 *
 * @author Tom
 */
public class Connexion {

    private static final Connexion instance = new Connexion();
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    private Connexion() {
        // Nothing
    }
    
    public static Connexion getInstance() {
        return instance;
    }

    /**
     * Connexion à la base de données avec paramètres
     * @param databaseName
     * @param databaseLogin
     * @param databasePassword
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connectLocal(String databaseName, String databaseLogin, String databasePassword) throws ClassNotFoundException, SQLException {

        String databaseUrl = "jdbc:mysql://localhost/" + databaseName;

        // Load the MySQL Drivers
        Class.forName("com.mysql.jdbc.Driver");

        // Setup a connection with the database
        this.connection = DriverManager.getConnection(databaseUrl, databaseLogin, databasePassword);

        // Statement for SQL query
        this.statement = connection.createStatement();
    }

    /**
     *  Effectue la requête passée en paramètre et affiche le résultat
     * @param statementQuery
     * @throws java.sql.SQLException
     */
    public void statementQuery(String statementQuery) throws SQLException {
        
        this.resultSet = this.statement.executeQuery(statementQuery);
        
        while (this.resultSet.next()) {
            // Stockage des valeurs de la ligne parcourue
            int id = this.resultSet.getInt("id");
            String author = this.resultSet.getString("author");
            String tittle = this.resultSet.getString("tittle");
            int year = this.resultSet.getInt("year");
            String publisher = this.resultSet.getString("publisher");
            
            // Affichage des résultats
            System.out.println("----------------------------------");
            System.out.println("Livre n°" + id);
            System.out.println("Auteur : " + author);
            System.out.println("Titre : " + tittle);
            System.out.println("Année : " + year);
            System.out.println("Editeur : " + publisher);
        }
    }
}

