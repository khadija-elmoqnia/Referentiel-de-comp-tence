package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO {

    private static final String url = "jdbc:mysql://localhost:3306/reference_competence";
    private static final String username = "root";
    private static final String password = "Root@12345";

    private static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(url, username, password);
    }

    public boolean addSkill(Skill skill) throws ClassNotFoundException {
        String sqlCheckExistence = "SELECT COUNT(*) FROM Skill WHERE nom = ?";
        String sqlInsertSkill = "INSERT INTO Skill (nom, description, domaine, categorie_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheckExistence);
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertSkill)) {
            
            // Vérifier si la compétence existe déjà avec le même nom
            pstmtCheck.setString(1, skill.getName());
            ResultSet rs = pstmtCheck.executeQuery();
            rs.next();
            int skillCount = rs.getInt(1);

            if (skillCount > 0) {
                System.out.println("La compétence existe déjà avec le même nom.");
                return false;
            }

            // Ajouter la compétence si elle n'existe pas
            pstmtInsert.setString(1, skill.getName());
            pstmtInsert.setString(2, skill.getDescription());
            pstmtInsert.setString(3, skill.getDomain());
            pstmtInsert.setInt(4, skill.getCategorie().getId());

            int rowsAffected = pstmtInsert.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<Skill> getAllSkills() throws ClassNotFoundException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM Skill";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setName(rs.getString("nom"));
                skill.setDescription(rs.getString("description"));
                skill.setDomain(rs.getString("domaine"));
                int categorieId = rs.getInt("categorie_id");
                CategorieDAO dao = new CategorieDAO();
                Categorie categorie = dao.getCategorieById(categorieId);
                skill.setCategorie(categorie);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public boolean deleteSkill(String name) throws ClassNotFoundException {
        String sqlUpdateProfilSkill = "UPDATE Profil_Skill SET skill_id = NULL WHERE skill_id = (SELECT id FROM Skill WHERE nom = ?)";
        String sqlDeleteSkill = "DELETE FROM Skill WHERE nom = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateProfilSkill);
             PreparedStatement pstmtDelete = conn.prepareStatement(sqlDeleteSkill)) {
            
            // Mettre à jour les liens Profil-Skill en rendant l'ID de compétence NULL
            pstmtUpdate.setString(1, name);
            pstmtUpdate.executeUpdate();
            
            // Supprimer la compétence
            pstmtDelete.setString(1, name);
            int rowsAffected = pstmtDelete.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateSkill(Skill updatedSkill,String nom) throws ClassNotFoundException {
        String sql = "UPDATE Skill SET nom = ?,description = ?, categorie_id = ? WHERE nom = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedSkill.getName());
            pstmt.setString(2, updatedSkill.getDescription());
            pstmt.setInt(3, updatedSkill.getCategorie_Id());
            pstmt.setString(4, nom);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public  static int getSkillIdByName(String SkillName) throws ClassNotFoundException {
        String sql = "SELECT id FROM Skill WHERE nom = ?";
        int skillId = -1; // Valeur par défaut si la catégorie n'est pas trouvée
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, SkillName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    skillId = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillId;
    }
    
    // Méthode pour récupérer une compétence par son nom
    public Skill getSkillByNom(String nom) {
        Skill skill = null;
        String query = "SELECT * FROM Skill WHERE nom = ?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nom);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    skill = new Skill();
                    skill.setName(resultSet.getString("nom"));
                    skill.setDescription(resultSet.getString("description"));
                    skill.setCategorie_Id(resultSet.getInt("categorie_id"));
                    // Vous pouvez ajouter d'autres attributs de compétence ici
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }
    
    public Skill getSkillById(int skillId) {
        String sql = "SELECT * FROM Skill WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, skillId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Skill skill = new Skill();
                    skill.setName(rs.getString("nom"));
                    skill.setDescription(rs.getString("description"));
                    skill.setDomain(rs.getString("domaine"));
                    // Vous pouvez également récupérer la catégorie associée si nécessaire
                    int categoryId = rs.getInt("categorie_id");
                    CategorieDAO categorieDAO = new CategorieDAO();
                    Categorie categorie = categorieDAO.getCategorieById(categoryId);
                    skill.setCategorie(categorie);
                    return skill;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
