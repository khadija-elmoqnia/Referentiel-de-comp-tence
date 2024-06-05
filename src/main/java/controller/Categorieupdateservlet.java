package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;
import model.CategorieDAO;
import model.Skill;
import model.SkillDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Skillupdateservlet
 */
public class Categorieupdateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorieupdateservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CategorieDAO catdao=new CategorieDAO();
		 
		 String oldname =request.getParameter("name");
		 
		 String nameupdated = request.getParameter("nameup");
         Categorie Categorieupdated = new Categorie();
         Categorieupdated.setNom(nameupdated);
         
         
		boolean successupdate = false;
		try {
			successupdate = catdao.updateCategorie(Categorieupdated,oldname );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         System.out.println("Attributs de la compétence mise à jour :");
         System.out.println("Nom : " + nameupdated +"oldname"+oldname);

         if (successupdate) {
             request.setAttribute("message", "La compétence a été modifiée avec succès.");
             System.out.println("message La compétence a été modifiée avec succès.");
         } else {
             request.setAttribute("erreur", "Erreur lors de la modification de la compétence.");
             System.out.println("Erreur lors de la modification de la compétence.");
         }

		

         RequestDispatcher dispatcherupdate = request.getRequestDispatcher("Categorieupdate.jsp");
         dispatcherupdate.forward(request, response);
         
	}

}