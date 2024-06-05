<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Categorie"%>
<%@page import="model.CategorieDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une compétence</title>
    <!-- Liens CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
   <div class="alert alert-success" role="alert" id="successAlert" style="display: none;">
        <% if (request.getAttribute("message") != null) { %>
            <%= request.getAttribute("message") %>
        <% } %>
    </div>
    <h2 class="mb-4">Ajouter une compétence</h2>
    <form action="AddSkillservlet" method="POST">

        <div class="form-group">
            <label for="name">Nom de la compétence:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="categorie">Catégorie:</label>
            <select class="form-control" id="categorie" name="categorie" required>
                <option value="">Sélectionnez une catégorie existante</option>
                <!-- Boucle pour afficher les catégories existantes -->
                <% 
                CategorieDAO catdao = new CategorieDAO();
                try {
                    ArrayList<Categorie> Categories = catdao.getAllCategories();
                    if (Categories != null) {
                        for (Categorie categorie : Categories) {
                %>
                <option value="<%= categorie.getId() %>"><%= categorie.getNom() %></option>
                <% 
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Cannot retrieve categories ");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                %>
            </select>
            <small class="form-text text-muted">Ou bien créez une nouvelle catégorie.</small>
            <a href="AddCategorie.jsp" class="btn btn-secondary">Ajouter une nouvelle catégorie</a>
        </div>

        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>

<!-- Scripts JavaScript -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        <% if (request.getAttribute("message") != null) { %>
            // Affiche l'alerte
            $('#successAlert').show();
            // Masque l'alerte après 2 secondes (2000 millisecondes)
            setTimeout(function() {
                $('#successAlert').fadeOut('slow');
            }, 2000); // 2 secondes
        <% } %>
    });
</script>
</body>
</html>

