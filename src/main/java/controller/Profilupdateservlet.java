package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Profil;
import model.ProfilDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Profilupdateservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Profilupdateservlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs du profil depuis les paramètres de la requête
        String titre = request.getParameter("titreup");
        String description = request.getParameter("descriptionup");
        String salaire = request.getParameter("salaireup");

        // Créer une instance de Profil avec les attributs récupérés
        Profil updatedProfil = new Profil(titre, description, Integer.valueOf(salaire));
        ProfilDAO profilDAO = new ProfilDAO();

        // Obtenir le titre du profil à partir de la requête
        String ancienTitre = request.getParameter("titre");

        // Mettre à jour le profil dans la base de données en utilisant ProfilDAO
        boolean success = false;
        try {
            success = profilDAO.updateProfil(updatedProfil, ancienTitre);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Rediriger l'utilisateur en fonction du succès ou de l'échec de la mise à jour du profil
        if (success) {
            request.setAttribute("message", "Le profil a été modifié avec succès.");
        } else {
            request.setAttribute("erreur", "Erreur lors de la modification du profil.");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Profilupdate.jsp");
        dispatcher.forward(request, response);
    }
}

