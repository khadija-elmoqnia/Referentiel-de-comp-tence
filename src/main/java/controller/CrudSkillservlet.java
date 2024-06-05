package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Skill;
import model.SkillDAO;
import model.Categorie;
import model.CategorieDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudSkillservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CrudSkillservlet() {
    }

    @SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*SkillDAO skillDAO = new SkillDAO();
    	List<Skill> skills = null;
    	try {
    	skills = skillDAO.getAllSkills();
    	} catch (ClassNotFoundException e) {
    	e.printStackTrace();
    	}
		if (skills != null) { // Vérifier si la liste de compétences n'est pas null
            request.setAttribute("skills", skills);
            System.out.println("Liste des compétences récupérées :");
            for (Skill skill : skills) {
                System.out.println("Nom : " + skill.getName() + ", Description : " + skill.getDescription() + ", Domaine : " + skill.getDomain() + ", Catégorie : " + skill.getCategorie().getNom());
            }
        } else {
            System.out.println("La liste des compétences est null.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CrudSkill.jsp");
        dispatcher.forward(request, response);*/
        String nameasuprime = request.getParameter("nomsup");
        System.out.println(" nameasuprime : " +  nameasuprime);
        SkillDAO skillDAOdelet = new SkillDAO();
	boolean successdelet = false;
	try {
		successdelet = skillDAOdelet.deleteSkill(nameasuprime);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

        System.out.println("Nom de la compétence à supprimer : " + nameasuprime);

        if (successdelet) {
            request.setAttribute("message", "La compétence a été supprimée avec succès.");
            System.out.println("message La compétence a été suppavec succès.");
        } else {
            request.setAttribute("erreur", "Erreur lors de la suppression de la compétence.");
            System.out.println("Erreur lors de la SUPP de la compétence.");
        }

	List<Skill> skillsapresdelet = null;
	try {
		skillsapresdelet = skillDAOdelet.getAllSkills();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        request.setAttribute("skills", skillsapresdelet);

        RequestDispatcher dispatcherdelet = request.getRequestDispatcher("CrudSkill.jsp");
        dispatcherdelet.forward(request, response);
      

   
}
    	
    	



    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    String nameasuprime = request.getParameter("nomsup");
                    System.out.println(" nameasuprime : " +  nameasuprime);
                    SkillDAO skillDAOdelet = new SkillDAO();
				boolean successdelet = false;
				try {
					successdelet = skillDAOdelet.deleteSkill(nameasuprime);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                    System.out.println("Nom de la compétence à supprimer : " + nameasuprime);

                    if (successdelet) {
                        request.setAttribute("message", "La compétence a été supprimée avec succès.");
                        System.out.println("message La compétence a été suppavec succès.");
                    } else {
                        request.setAttribute("erreur", "Erreur lors de la suppression de la compétence.");
                        System.out.println("Erreur lors de la SUPP de la compétence.");
                    }

				List<Skill> skillsapresdelet = null;
				try {
					skillsapresdelet = skillDAOdelet.getAllSkills();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    request.setAttribute("skills", skillsapresdelet);

                    RequestDispatcher dispatcherdelet = request.getRequestDispatcher("CrudSkill.jsp");
                    dispatcherdelet.forward(request, response);
                  

               
            }
       
    }



