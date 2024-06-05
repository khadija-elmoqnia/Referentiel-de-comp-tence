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
public class AddCategorieservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategorieservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CategorieDAO catdao = new CategorieDAO();

	    String name = request.getParameter("name");
	    Categorie categori = new Categorie(name);

	    System.out.println("Nom : " + name );

	    boolean success = false;
	    try {
	        success =  catdao.addCategorie(categori);
	    } catch (ClassNotFoundException e) {
	        // Gérer l'exception en affichant ou en journalisant l'erreur
	        e.printStackTrace();
	        
	    }

	    if (success) {
	        request.setAttribute("message", "La compétence a été ajoutée avec succès.");
	    } else {
	        request.setAttribute("erreur", "Erreur lors de l'ajout de la compétence.");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("AddCategorie.jsp");
	    dispatcher.forward(request, response);
	}


}