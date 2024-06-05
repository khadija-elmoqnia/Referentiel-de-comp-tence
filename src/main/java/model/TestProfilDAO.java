package model;


import java.util.List;

import model.Profil;
import model.ProfilDAO;

public class TestProfilDAO {

    public static void main(String[] args) throws ClassNotFoundException {
        ProfilDAO profilDAO = new ProfilDAO();

        // Test d'ajout de profil
        testAddProfil(profilDAO);

        // Test de récupération de tous les profils
        testGetAllProfils(profilDAO);

        // Test de suppression de profil
        testDeleteProfil(profilDAO);

        // Test de mise à jour de profil
        testUpdateProfil(profilDAO);
    }

    public static void testAddProfil(ProfilDAO profilDAO) throws ClassNotFoundException {
        Profil profil = new Profil();
        profil.setTitre("Ingénieur logiciel");
        profil.setDescription("Développeur expérimenté avec de solides compétences en Java et en Spring");
        profil.setSalaire(6000);

        boolean success = profilDAO.addProfil(profil);
        if (success) {
            System.out.println("Profil ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du profil.");
        }
    }

    public static void testGetAllProfils(ProfilDAO profilDAO) {
        try {
            List<Profil> profils = profilDAO.getAllProfils();
            System.out.println("Liste des profils :");
            for (Profil profil : profils) {
                System.out.println(profil);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testDeleteProfil(ProfilDAO profilDAO) throws ClassNotFoundException {
        String titre = "Ingénieur logiciel";
        boolean success = profilDAO.deleteProfil(titre);
        if (success) {
            System.out.println("Profil supprimé avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression du profil.");
        }
    }

    public static void testUpdateProfil(ProfilDAO profilDAO) throws ClassNotFoundException {
        String ancienTitre = "Ingénieur logiciel";
        String nouveauTitre = "Développeur Full Stack";
        Profil updatedProfil = new Profil();
        updatedProfil.setTitre(nouveauTitre);
        updatedProfil.setDescription("Développeur Full Stack avec expertise en technologies front-end et back-end");
        updatedProfil.setSalaire(70000);

        boolean success = profilDAO.updateProfil(updatedProfil, ancienTitre);
        if (success) {
            System.out.println("Profil mis à jour avec succès !");
        } else {
            System.out.println("Erreur lors de la mise à jour du profil.");
        }
    }
}
