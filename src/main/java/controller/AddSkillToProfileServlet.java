package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProfilDAO;

import java.io.IOException;


public class AddSkillToProfileServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String skillIdStr = request.getParameter("skill");
        String profilIdStr = request.getParameter("profilid");
        String niveauRequisStr = request.getParameter("niveau_requis");
        String profil = request.getParameter("profil");

        // Ajoutez des journaux pour déboguer
        System.out.println("Skill ID: " + skillIdStr);
        System.out.println("Profil ID: " + profilIdStr);
        System.out.println("Niveau requis: " + niveauRequisStr);
        System.out.println("Profil: " + profil);

        // Vérifiez si les paramètres ne sont pas vides ou nuls
        if (skillIdStr != null && !skillIdStr.isEmpty() &&
            profilIdStr != null && !profilIdStr.isEmpty() &&
            niveauRequisStr != null && !niveauRequisStr.isEmpty()) {
            try {
                int skillId = Integer.parseInt(skillIdStr);
                int profilId = Integer.parseInt(profilIdStr);
                int niveauRequis = Integer.parseInt(niveauRequisStr);

                // Ajoutez la compétence au profil
                ProfilDAO.addSkillToProfile(profilId, skillId, niveauRequis);

                // Redirigez vers la page JSP avec le paramètre profil mis à jour
                response.sendRedirect("Competence_profil.jsp?profil=" + profil);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("Competence_profil.jsp?profil=" + profil + "&error=invalid_input");
            }
        } else {
            // Si les paramètres sont vides ou nuls, redirigez avec un message d'erreur
            response.sendRedirect("Competence_profil.jsp?profil=" + profil + "&error=missing_input");
        }
    }
}

