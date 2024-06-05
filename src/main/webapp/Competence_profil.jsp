<%@page import="model.Profil"%>
<%@page import="model.ProfilDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Skill"%>
<%@page import="model.SkillDAO"%>
<%@page import="model.Categorie"%>
<%@page import="model.CategorieDAO"%>
<%@page import="model.ProfilSkill"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Compétences Associées</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            color: #007bff;
            margin-top: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table th, table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        table tr:last-child td {
            border-bottom: none;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-control {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
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
    </style>
</head>
<body>
    <h1>Liste des Compétences Associées</h1>
    <table>
        <thead>
            <tr>
                <th>Nom de Compétence</th>
                <th>Description</th>
                <th>Catégorie</th>
                <th>Niveau requis</th>
            </tr>
        </thead>
        <tbody>
            <% 
            String titreProfil = request.getParameter("profil") != null ? request.getParameter("profil") : (String) request.getAttribute("profil");
            int profilId = ProfilDAO.getidforprofil(titreProfil);

            if (profilId != -1) {
                List<ProfilSkill> profilSkills = ProfilDAO.getProfilSkills(profilId);
                for (ProfilSkill profilSkill : profilSkills) {
                    Skill skill = profilSkill.getSkill();
                    int niveauRequis = profilSkill.getNiveauRequis();
                    %>
                    <tr>
                        <td><%= skill.getName() %></td>
                        <td><%= skill.getDescription() %></td>
                        <td><%= skill.getCategorie() != null ? skill.getCategorie().getNom() : "N/A" %></td>
                        <td><%= niveauRequis %></td>
                    </tr>
                    <%
                }
            } else {
                %>
                <tr>
                    <td colspan="5">Profil non trouvé.</td>
                </tr>
                <%
            }
            %>
        </tbody>
    </table>
    <h3>Ajouter une compétence au profil</h3>
<form action="AddSkillToProfileServlet" method="post">
    <div class="form-group">
        <label for="skill">Sélectionner une compétence :</label>
        <select class="form-control" id="skill" name="skill">
            <% 
            List<Skill> skills = SkillDAO.getAllSkills();
            for (Skill skill : skills) {
            %>
            <option value="<%= skill.getId() %>"><%= skill.getName() %></option>
            <% } %>
        </select>
    </div>
    <div class="form-group">
        <label for="niveau_requis">Niveau requis : nombre d'années de maîtrise</label>
        <input class="form-control" id="niveau_requis" name="niveau_requis" type="number">
    </div>
    <input type="hidden" name="profilid" value="<%= profilId %>">
    <input type="hidden" name="profil" value="<%= titreProfil %>">
    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>
</body>
</html>