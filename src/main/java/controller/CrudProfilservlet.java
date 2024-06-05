package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProfilDAO;
import java.io.IOException;

public class CrudProfilservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CrudProfilservlet() {
    }

    @SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	     String nameasuprime = request.getParameter("nomsup");
   	        System.out.println(" nameasuprime : " +  nameasuprime);
   	        ProfilDAO profilDAOdelet = new ProfilDAO();
   		boolean successdelet = false;
   		try {
   			successdelet = profilDAOdelet.deleteProfil(nameasuprime);
   		} catch (ClassNotFoundException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

   	        System.out.println("Nom de la compétence à supprimer : " + nameasuprime);

   	        if (successdelet) {
   	            request.setAttribute("message", "Le profil a été supprimée avec succès.");
   	            System.out.println("message profila été suppavec succès.");
   	        } else {
   	            request.setAttribute("erreur", "Erreur lors de la suppression du Profil");
   	            System.out.println("Erreur lors de la SUPP du profil.");
   	        }


   	        RequestDispatcher dispatcherdelet = request.getRequestDispatcher("CrudProfil.jsp");
   	        dispatcherdelet.forward(request, response);
          
       }
     
     
   
}
    	  

