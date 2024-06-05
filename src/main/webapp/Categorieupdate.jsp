<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Categorie"%>
<%@page import="model.Skill"%>
<%@page import="model.CategorieDAO"%>
<%@page import="model.SkillDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une Catégorie</title>
    <!-- Liens CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        option.highlighted {
        background-color: yellow;
        font-weight: bold;
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
    <h2 class="mb-4">Modifier la Categorie</h2>
    <%
        String catnom = request.getParameter("nomcat");
        String name ="";
      if (catnom!= null) {
         name = catnom;
         
}
%>

    <form action="Categorieupdateservlet" method="POST">
       

        <div class="form-group">
            <label for="name">Nouveau nom de la Categorie:</label>
            <input type="text" class="form-control" id="name" name="nameup" value="<%= name %>"required>
            
        </div>
        <input type="hidden" name="name" value="<%= name %>">
        <button type="submit" class="btn btn-primary">Modifier</button>
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
