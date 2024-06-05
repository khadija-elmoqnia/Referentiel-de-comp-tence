package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.CategorieDAO;
import model.Skill;
import model.SkillDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class AddSkillservlet
 */
public class AddSkillservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSkillservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieDAO catdao = new CategorieDAO();
        try {
            ArrayList<Categorie> Categories = catdao.getAllCategories();
            request.setAttribute("Categories", Categories);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot retrieve categories ");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddSkill.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CategorieDAO catdao = new CategorieDAO();

	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String categorie = request.getParameter("categorie");
	    Categorie categori = null;
		try {
			categori = catdao.getCategorieById(Integer.valueOf(categorie));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   
	    

	    Skill skill1 = new Skill(name, description, null, categori);
	    SkillDAO skillDAO = new SkillDAO();

	    System.out.println("Attributs de la nouvelle compétence à ajouter :");
	    System.out.println("Nom : " + name + ", Description : " + description + " Catégorie : " + categorie);

	    boolean success = false;
	    try {
	        success = skillDAO.addSkill(skill1);
	    } catch (ClassNotFoundException e) {
	        // Gérer l'exception en affichant ou en journalisant l'erreur
	        e.printStackTrace();
	        // Rediriger vers une page d'erreur
	       //request.setAttribute("erreur", "Erreur lors de l'ajout de la compétence.");
	        //RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
	        //dispatcher.forward(request, response);
	        return; // Arrêter le traitement de la requête
	    }

	    if (success) {
	        request.setAttribute("message", "La compétence a été ajoutée avec succès.");
	    } else {
	        request.setAttribute("erreur", "Erreur lors de l'ajout de la compétence.");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("AddSkill.jsp");
	    dispatcher.forward(request, response);
	}


}
