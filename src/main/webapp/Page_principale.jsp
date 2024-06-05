<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Référentiel des compétences</title>
    <!-- Liens CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .btn-primary, .btn-secondary {
            width: 100%;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-4">Bienvenue dans le référentiel des compétences</h2>
    <p>Ce site est un référentiel des compétences qui lie des profils à des compétences. Les utilisateurs peuvent découvrir les compétences nécessaires pour suivre un profil et ajouter de nouvelles informations pour que d'autres utilisateurs puissent les consulter.</p>
    <p>Vous pouvez utiliser les boutons ci-dessous pour naviguer sur le site :</p>
    <a href="CrudProfil.jsp" class="btn btn-primary">Consulter les profils</a>
    <a href="CrudCategorie.jsp" class="btn btn-primary">Consulter les catégories</a>
     <a href="CrudSkill.jsp" class="btn btn-primary">Consulter les Compétences</a>
     
     <a href="Login.jsp" class="btn btn-secondary">Déconnexion</a>
</div>

<!-- Scripts JavaScript -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
