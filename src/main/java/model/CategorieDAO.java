package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategorieDAO {
	   private static final String url ="jdbc:mysql://localhost:3306/reference_competence";
	    private static final String username = "root";
	    private static final String password = "Root@12345";
	 
	 private  static Connection getConnection2() throws SQLException, ClassNotFoundException {
	 return DriverManager.getConnection(url, username, password);
	 }

	
	 public static  Categorie getCategorieById(int id) throws ClassNotFoundException {
		    String sql = "SELECT * FROM Categorie WHERE id = ?";
		    try (   Connection conn = getConnection2();
		    		PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                return new Categorie(
		                        rs.getInt("id"),
		                        rs.getString("nom")
		                );
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return null; // Retourne null si aucune catégorie trouvée
		}
	 
	 public  ArrayList<Categorie> getAllCategories() throws SQLException, ClassNotFoundException {
		 ArrayList<Categorie> categories = new ArrayList<>();
	        String query = "SELECT * FROM categorie";
	        
	        try (
	        		Connection conn = getConnection2();PreparedStatement statement = conn.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String nom = resultSet.getString("nom");
	                Categorie categorie = new Categorie(id, nom);
	                categories.add(categorie);
	            }
	        }
	        return categories;
	    }
	 
	 public  int getCategorieIdByName(String categoryName) throws ClassNotFoundException {
	        String sql = "SELECT id FROM Categorie WHERE nom = ?";
	        int categoryId = -1; // Valeur par défaut si la catégorie n'est pas trouvée
	        try (Connection conn = getConnection2();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, categoryName);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    categoryId = rs.getInt("id");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return categoryId;
	    }
	 

	    public boolean deleteCategorie(Categorie cat) throws ClassNotFoundException {
	        String sql = "DELETE  FROM Categorie WHERE nom = ?";
	        try (Connection conn = getConnection2(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1,cat.getNom());
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean updateCategorie(Categorie updatedCategorie,String oldname) throws ClassNotFoundException {
	        String sql = "UPDATE Categorie SET nom = ? WHERE nom = ?";
	        try (Connection conn = getConnection2(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, updatedCategorie.getNom());
	            pstmt.setString(2, oldname);
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public boolean addCategorie(Categorie categorie) throws ClassNotFoundException {
	        String sql = "INSERT INTO Categorie (nom) VALUES (?)";
	        try (Connection conn = getConnection2(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, categorie.getNom());
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
