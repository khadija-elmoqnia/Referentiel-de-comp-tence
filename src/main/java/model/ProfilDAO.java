package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfilDAO {

    private static final String url = "jdbc:mysql://localhost:3306/reference_competence";
    private static final String username = "root";
    private static final String password = "Root@12345";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public boolean addProfil(Profil profil) throws ClassNotFoundException {
        String sql = "INSERT INTO Profil (titre, description, salaire) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profil.getTitre());
            pstmt.setString(2, profil.getDescription());
            pstmt.setInt(3, profil.getSalaire());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public List<Profil> getAllProfils() throws ClassNotFoundException {
        List<Profil> profils = new ArrayList<>();
        String sql = "SELECT * FROM Profil";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Profil profil = new Profil();
                profil.setTitre(rs.getString("titre"));
                profil.setDescription(rs.getString("description"));
                profil.setSalaire(rs.getInt("salaire"));
                profils.add(profil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profils;
    }

    public boolean deleteProfil(String titre) throws ClassNotFoundException {
        String sqlUpdateProfilSkill = "UPDATE Profil_Skill SET profil_id = NULL WHERE profil_id = (SELECT id FROM Profil WHERE titre = ?)";
        String sqlDeleteProfil = "DELETE FROM Profil WHERE titre = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateProfilSkill);
             PreparedStatement pstmtDelete = conn.prepareStatement(sqlDeleteProfil)) {
            
            // Mettre à jour les liens Profil-Skill en rendant l'ID de profil NULL
            pstmtUpdate.setString(1, titre);
            pstmtUpdate.executeUpdate();
            
            // Supprimer le profil
            pstmtDelete.setString(1, titre);
            int rowsAffected = pstmtDelete.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateProfil(Profil updatedProfil, String titre) throws ClassNotFoundException {
        String sql = "UPDATE Profil SET titre = ?, description = ?, salaire = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedProfil.getTitre());
            pstmt.setString(2, updatedProfil.getDescription());
            pstmt.setInt(3, updatedProfil.getSalaire());
            int id =getidforprofil(titre);
            pstmt.setInt(4,id );
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public  static int getidforprofil(String titre) throws ClassNotFoundException {
        String sql = "SELECT id FROM Profil WHERE titre = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retourne -1 si le titre de compétence n'est pas trouvé
    }

    public Profil getProfilById(int profilId) {
        String sql = "SELECT * FROM Profil WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profilId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Profil profil = new Profil();
                    profil.setTitre(rs.getString("titre"));
                    profil.setDescription(rs.getString("description"));
                    profil.setSalaire(rs.getInt("salaire"));
                    return profil;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Skill> getSkillsforprofil(int Id) throws ClassNotFoundException {
    	SkillDAO dao=new SkillDAO();
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT skill_id FROM Profil_Skill WHERE profil_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Id);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                int skill_id = res.getInt("skill_id");
                // Maintenant, vous devez récupérer les informations sur les compétences en fonction de leur ID
                Skill skill = dao.getSkillById(skill_id); // Vous devez implémenter cette méthode pour récupérer les informations sur la compétence
                if (skill != null) {
                    skills.add(skill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
    

    public Profil getProfilByTitre(String titre) throws ClassNotFoundException {
        String sql = "SELECT * FROM Profil WHERE titre = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Profil profil = new Profil();
                    profil.setTitre(rs.getString("titre"));
                    profil.setDescription(rs.getString("description"));
                    profil.setSalaire(rs.getInt("salaire"));
                    return profil;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si le profil n'est pas trouvé
    }
    
   /* public static List<Skill> getSkillsByProfileId(int profileId) {
        List<Skill> skills = new ArrayList<>();

        String query = "SELECT s.id, s.nom, s.description, s.domaine, s.categorie_id " +
                       "FROM Skill s " +
                       "JOIN Profil_Skill ps ON s.id = ps.skill_id " +
                       "WHERE ps.profil_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Skill skill = new Skill();
                    skill.setId(rs.getInt("id"));
                    skill.setName(rs.getString("nom"));
                    skill.setDescription(rs.getString("description"));
                    skill.setDomain(rs.getString("domaine"));
                 // Créer une instance de Categorie et assigner le nom de la catégorie
                    Categorie categorie = new Categorie();
                    categorie = CategorieDAO.getCategorieById(rs.getInt("categorie_id"));
                    
                    // Assigner l'instance de Categorie à la propriété categorie de Skill
                    skill.setCategorie(categorie);
                    skills.add(skill);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    } */
    
    
    public static List<ProfilSkill> getProfilSkills(int profilId) {
        List<ProfilSkill> profilSkills = new ArrayList<>();
        String sql = "SELECT ps.niveau_requis, s.* " +
                     "FROM Profil_Skill ps " +
                     "JOIN Skill s ON ps.skill_id = s.id " +
                     "WHERE ps.profil_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profilId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int niveauRequis = rs.getInt("niveau_requis");
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setName(rs.getString("nom"));
                skill.setDescription(rs.getString("description"));
                skill.setDomain(rs.getString("domaine"));
                
                Categorie categorie = null;
                try {
                    categorie = CategorieDAO.getCategorieById(rs.getInt("categorie_id"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                skill.setCategorie(categorie);
                
                profilSkills.add(new ProfilSkill(skill, niveauRequis));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profilSkills;
    }
    

    public static void addSkillToProfile(int profilId, int skillId,int niveau_requis) {
        String sql = "INSERT INTO Profil_Skill (profil_id, skill_id, niveau_requis) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); // Obtenez votre connexion à la base de données ici
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Définir les valeurs des paramètres dans la requête SQL
            pstmt.setInt(1, profilId);
            pstmt.setInt(2, skillId);
            pstmt.setInt(3, niveau_requis); 

            // Exécuter la requête SQL
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions SQL ici
        }
    }

    public static int getNiveauRequisForSkillAndProfil(int skillId, int profilId) {
        int niveauRequis = -1; // Par défaut, niveau requis non trouvé

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT niveau_requis " +
                     "FROM Profil_Skill " +
                     "WHERE profil_id = ? AND skill_id = ?")) {
            pstmt.setInt(1, profilId);
            pstmt.setInt(2, skillId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Si une entrée correspondante est trouvée, récupérer le niveau requis
                niveauRequis = rs.getInt("niveau_requis");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return niveauRequis;
    }
    	
    	
    	
    }

