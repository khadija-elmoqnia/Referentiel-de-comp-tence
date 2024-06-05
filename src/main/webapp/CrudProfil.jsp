<%@page import="model.Profil"%>
<%@page import="model.ProfilDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultation des Profils</title>
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

    <h3>Liste des Profils</h3>
    
    <div class="text-center mt-3 d-flex justify-content-end">
        <a href="AddProfil.jsp" class="btn btn-success" role="button">Ajouter Un Profil</a>
        <br> <br><br>
         
         
    </div>
    <table class="table">         		
        <thead>
            <tr>
                <th>Titre</th>
                <th>Description</th>
                <th>Salaire</th>
                <th>Compétences</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="profilTableBody">
            <%-- Retrieve the skills attribute from the request --%>
            <%
                
                ProfilDAO profilDAO = new ProfilDAO();
                List<Profil> profilList = profilDAO.getAllProfils();
                    
                    if (profilList == null || profilList.isEmpty()) {
            %>
                        <tr>
                            <td colspan="5" class="no-competence"> Aucun Profil  à afficher.</td>
                        </tr>
            <%
                    } else {
                        for (Profil profil :  profilList) {
            %>
                            <tr>
                                <td><%= profil.getTitre() %></td>
                                <td><%= profil.getDescription() %></td>
                                <td><%= profil.getSalaire() %></td>
                                <td>
                                 <div class="d-flex">
                                        <a href="Competence_profil.jsp?profil=<%= profil.getTitre() %>" class="btn btn-primary btn-sm" role="button">
                                        
                                            List des compétences associées
                                        </a>
                                        
                                    </div>
                                </td>
                                <td>
                                    <!-- Your action buttons for editing and deleting -->
                                    <div class="d-flex">
                                        <a href="Profilupdate.jsp?titre=<%= profil.getTitre() %>" class="btn btn-primary btn-sm" role="button">
                                            Éditer
                                        </a><br><br><br>
                                        <a href="CrudProfilservlet?nomsup=<%= profil.getTitre() %>" class="btn btn-danger btn-sm" role="button">
                                        Supprimer
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

