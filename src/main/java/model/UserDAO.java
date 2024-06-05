package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	  private static final String url = "jdbc:mysql://localhost:3306/reference_competence";
	    private static final String username = "root";
	    private static final String password = "Root@12345";
	    
	    private Connection getConnection() throws SQLException{
	        
	        return DriverManager.getConnection(url, username, password);
	    }

	    public boolean authenticate(String username, String password) {
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // Établir une connexion à la base de données
	        	Connection connection = getConnection();

	            // Requête SQL pour vérifier l'existence de l'utilisateur avec les informations d'identification fournies
	            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	            statement = connection.prepareStatement(query);
	            statement.setString(1, username);
	            statement.setString(2, password);

	            // Exécuter la requête
	            resultSet = statement.executeQuery();

	            // Si l'utilisateur existe dans la base de données, la requête renverra une ligne
	            // Sinon, aucun utilisateur correspondant n'a été trouvé
	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Gérer les exceptions ici (enregistrer dans les journaux, afficher un message d'erreur, etc.)
	            return false;
	        }
	    }
    
    public boolean createUser(User user) {
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            // Établir une connexion à la base de données
             Connection  conn = getConnection();

            // Préparer la requête SQL pour insérer un nouvel utilisateur
            String sql = "INSERT INTO users (USERNAME, PASSWORD, firstName, email) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getEmail());

            // Exécuter la requête
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return success;
    }

}
