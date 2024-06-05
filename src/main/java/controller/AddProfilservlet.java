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

public class AddProfilservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProfilservlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs du profil depuis les paramètres de la requête
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String salaire = request.getParameter("salaire");

        // Créer une instance de Profil avec les attributs récupérés
        Profil profil = new Profil(titre, description,Integer.valueOf(salaire));
        ProfilDAO profilDAO = new ProfilDAO();

        // Ajouter le profil à la base de données en utilisant ProfilDAO
        boolean success = false;
        try {
            success = profilDAO.addProfil(profil);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Rediriger l'utilisateur en fonction du succès ou de l'échec de l'ajout du profil
        if (success) {
            request.setAttribute("message", "Le profil a été ajouté avec succès.");
        } else {
            request.setAttribute("erreur", "Erreur lors de l'ajout du profil.");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddProfil.jsp");
        dispatcher.forward(request, response);
    }
}

