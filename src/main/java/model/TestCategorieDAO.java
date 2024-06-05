package model;


import java.util.List;

public class TestCategorieDAO {
	 public static void main(String[] args) throws ClassNotFoundException {
	       

	        // Nom de la compétence à supprimer
	        String catt = "Informatiquemodd";
	        Categorie cat = new Categorie(catt);

	        // Tester la suppression de la compétence
	        boolean deleteSuccess = deleteCat(cat);

	        if (deleteSuccess) {
	            System.out.println("La compétence " + cat + " a été supprimée avec succès !");
	        } else {
	            System.out.println("Erreur lors de la suppression de la compétence " +cat + ".");
	        }
	    }

	    public static void testGetAllSkills() throws ClassNotFoundException {
	        SkillDAO skillDAO = new SkillDAO();
	        List<Skill> skills = skillDAO.getAllSkills();
	        if (skills.isEmpty()) {
	            System.out.println("Aucune compétence n'a été trouvée.");
	        } else {
	            System.out.println("Liste des compétences :");
	            for (Skill skill : skills) {
	                System.out.println("Nom : " + skill.getName() + ", Description : " + skill.getDescription() + ", Domaine : " + skill.getDomain() + ", Catégorie : " + skill.getCategorie().getNom());
	            }
	        }
	    }

	    public static void testAddSkill() throws ClassNotFoundException {
	        // Création d'une nouvelle compétence à ajouter
	        Skill newSkill = new Skill();
	        CategorieDAO dao=new CategorieDAO();
	        newSkill.setName("Nouvelle compétence");
	        newSkill.setDescription("Description de la nouvelle compétence");
	        newSkill.setDomain("Domaine de la nouvelle compétence");

	        // Récupération de l'ID de la catégorie existante
	        int categorieId = -1;
			try {
				categorieId = dao.getCategorieIdByName("Informatique");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (categorieId != -1) {
	            // La catégorie existe, on peut créer la compétence
	            Categorie categorie = new Categorie();
	            categorie.setId(categorieId);

	            newSkill.setCategorie(categorie);

	            // Ajout de la nouvelle compétence
	            SkillDAO skillDAO = new SkillDAO();
	            boolean success = skillDAO.addSkill(newSkill);
	            if (success) {
	                System.out.println("La nouvelle compétence a été ajoutée avec succès.");
	            } else {
	                System.out.println("Erreur lors de l'ajout de la nouvelle compétence.");
	            }
	        } else {
	            System.out.println("La catégorie n'existe pas dans la base de données.");
	        }
	    }
	    public static void testGetSkillByNom() {
	        SkillDAO dao = new SkillDAO();
	        Skill skill = dao.getSkillByNom("PHP"); // Remplacez "NomDeLaCompetence" par le nom réel de la compétence à tester
	        if (skill != null) {
	            System.out.println("Skill found:");
	            System.out.println("Name: " + skill.getName());
	            System.out.println("Description: " + skill.getDescription());
	            System.out.println("Category ID: " + skill.getCategorie_Id());
	        } else {
	            System.out.println("Skill not found.");
	        }
	    }

	    public static void testGetCategorieById() throws ClassNotFoundException {
	        CategorieDAO dao = new CategorieDAO();
	        Categorie categorie = dao.getCategorieById(1); // Remplacez 1 par l'ID de la catégorie à tester
	        if (categorie != null) {
	            System.out.println("Category found:");
	            System.out.println("ID: " + categorie.getId());
	            System.out.println("Name: " + categorie.getNom());
	        } else {
	            System.out.println("Category not found.");
	        }
	    }
	    
	    public static void testGetSkillIdByName() throws ClassNotFoundException {
	        SkillDAO dao = new SkillDAO();
	        String skillName = "Communication"; // Remplacez "NomDeLaCompetence" par le nom réel de la compétence à tester
	        int skillId = dao.getSkillIdByName(skillName);
	        if (skillId != -1) {
	            System.out.println("Skill ID for skill '" + skillName + "': " + skillId);
	        } else {
	            System.out.println("Skill with name '" + skillName + "' not found.");
	        }
	    }
	    public static boolean deleteCat(Categorie cat) {
	        try {
	        	CategorieDAO dao =new CategorieDAO();	            // Appel de la méthode deleteSkill de SkillDAO pour supprimer la compétence
	            return dao.deleteCategorie(cat);
	        } catch (ClassNotFoundException e) {
	            System.out.println("ClassNotFoundException: " + e.getMessage());
	            return false;
	        }
	    }
	}

