package modele;

/**
 *
 * Librairies importées
 */
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tom
 */
public class Connexion {

    // Design pattern Singleton, une unique instance 
    private static final Connexion INSTANCE = new Connexion();
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private boolean connectionEstablihed = false;
    
    // Nom des tables dans la base de données "hopital"
    private final String[] tablesNames = {"chambre", "docteur", "employe", "hospitalisation", "infirmier", "malade", "service", "soigne"};
    
    private Connexion() {
        // Nothing
    }
    
    /**
     * Retourne l'unique instance de la classe Connexion
     * @return INSTANCE
     */
    public static Connexion getInstance() {
        return INSTANCE;
    }
    
    /**
     * Retourne la valeur indiquant si la BBD est connectée
     * @return Booléen informant sur la connexion
     */
    public boolean isConnected() {
        return this.connectionEstablihed;
    }
    
    /**
     * Retourne le nom des tables dans la BBD
     * @return tablesNames
     */
    public String[] getTablesNames() {
        return this.tablesNames;
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
        
        // URL of the database
        String databaseUrl = "jdbc:mysql://localhost/" + databaseName;

        // Load the MySQL Drivers
        Class.forName("com.mysql.jdbc.Driver");

        // Setup a connection with the database
        connection = DriverManager.getConnection(databaseUrl, databaseLogin, databasePassword);
        
        // La connexion avec la base de données a été établie
        if(connection != null) {
            connectionEstablihed = true;
        }
        
        // Statement for SQL query
        statement = connection.createStatement();
    }

    /**
     *  Effectue la requête passée en paramètre et affiche le résultat
     * @param tableName
     * @param columnName
     * @param value
     * @throws java.sql.SQLException
     */
    public void searchTable(String tableName, String columnName, String value) throws SQLException {
        
        String querySQL = null;
        
        if(columnName.equals("tout")) {
            querySQL = "select * from " + tableName;
        }
        else if(value.equals("")) {
            querySQL = "select " + columnName + " from " + tableName;
        }
        else {
            querySQL = "select * from " + tableName + " where " + columnName + "='" + value + "'";
        }
        
        resultSet = statement.executeQuery(querySQL);
        
        // It creates and displays the table
        JTable table = new JTable(buildTableModel(resultSet));

        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}

