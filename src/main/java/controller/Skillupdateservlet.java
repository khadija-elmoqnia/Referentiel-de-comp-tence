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
public class Skillupdateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Skillupdateservlet() {
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
		 SkillDAO skillDAOupdate = new SkillDAO();
		 String oldname =request.getParameter("nom");
		 
		 String nameupdated = request.getParameter("nameup");
         String descriptionupdated = request.getParameter("descriptionup");
         String categorieupdated = request.getParameter("categorieup");
         Categorie Categorieupdated = null;
		
         
         Skill skillupdated = new Skill();
         skillupdated.setName(nameupdated);
         skillupdated.setDescription(descriptionupdated);
         skillupdated.setCategorie_Id(Integer.valueOf(categorieupdated));
         
		boolean successupdate = false;
		try {
			successupdate = skillDAOupdate.updateSkill(skillupdated,oldname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         System.out.println("Attributs de la compétence mise à jour :");
         System.out.println("Nom : " + nameupdated + ", Description : " + descriptionupdated + ", Catégorie : " + categorieupdated+"oldname"+oldname);

         if (successupdate) {
             request.setAttribute("message", "La compétence a été modifiée avec succès.");
             System.out.println("message La compétence a été modifiée avec succès.");
         } else {
             request.setAttribute("erreur", "Erreur lors de la modification de la compétence.");
             System.out.println("Erreur lors de la modification de la compétence.");
         }

		List<Skill> skillsapresupdate = null;
		try {
			skillsapresupdate = skillDAOupdate.getAllSkills();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         request.setAttribute("skills", skillsapresupdate);

         RequestDispatcher dispatcherupdate = request.getRequestDispatcher("Skillupdate.jsp");
         dispatcherupdate.forward(request, response);
         
	}

}
