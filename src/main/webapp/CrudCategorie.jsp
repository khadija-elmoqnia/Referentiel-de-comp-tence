<%@page import="model.Skill"%>
<%@page import="model.SkillDAO"%>
<%@page import="model.Categorie"%>
<%@page import="model.CategorieDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultation des Catégories</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .no-competence {
            padding: 10px;
            background-color: #f2dede;
            border: 1px solid #ebccd1;
            border-radius: 4px;
            color: #a94442;
            margin-bottom: 10px;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            margin-bottom: 20px; /* Ajout de marge en bas */
        }
        .table th, .table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .table tr:last-child td {
            border-bottom: none;
        }
        .btn {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-primary {
            background-color: #007bff;
            color: #fff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-danger {
            background-color: #dc3545;
            color: #fff;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
        .btn-success {
            background-color: #28a745;
            color: #fff;
        }
        .btn-success:hover {
            background-color: #218838;
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

    <h3>Liste des Categorie</h3>
    
    <!-- Bouton pour ajouter une nouvelle compétence -->
    <div class="text-center mt-3 d-flex justify-content-end">
        <a href="AddCategorie.jsp" class="btn btn-success" role="button">Ajouter une Categorie</a>
        <br> <br><br>
         
         
    </div>
   <%
    Object testObject = request.getAttribute("Test");
    if (testObject != null) {
        String test = (String) testObject;
%>
        <h4> <%= test %> </h4>
<%
    }
%>
    <table class="table">         		
        <thead>
            <tr>
                <th>Nom</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="skillsTableBody">
            <%-- Retrieve the skills attribute from the request --%>
            <%
               // Object skillsObject = request.getAttribute("skills");
               // System.out.println("Contenu de la liste skills : " + skillsObject);
                
               // if (skillsObject instanceof List) {
                //    List<Skill> skillsList = (List<Skill>) skillsObject;
                
                CategorieDAO catDAO = new CategorieDAO();
                ArrayList<Categorie> catList = catDAO.getAllCategories();
                    
                    if (catList .isEmpty()) {
            %>
                        <tr>
                            <td colspan="5" class="no-competence">Aucune Categorie à afficher.</td>
                        </tr>
            <%
                    } else {
                        for (Categorie cat : catList ) {
            %>
                            <tr>
                                <td><%= cat.getNom() %></td>
                                <td>
                                    <!-- Your action buttons for editing and deleting -->
                                    <div class="d-flex">
                                        <a href="Categorieupdate.jsp?nomcat=<%= cat.getNom() %>" class="btn btn-primary btn-sm" role="button">
                                            Éditer
                                        </a>
                                    </div>
                                </td>
                            </tr>
            <%
                        }
                    }
                
            %>
        </tbody>
    </table>
    
</div>

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

