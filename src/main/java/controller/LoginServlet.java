package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.UserDAO;

@WebServlet({"/login", "/register"})
public class LoginServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        
        if (path.equals("/login")) {
            // Process login request
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userDAO.authenticate(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("Page_principale.jsp"); // Redirection vers la page principale après authentification réussie
            } else {
                request.setAttribute("message", "Nom d'utilisateur ou mot de passe incorrect.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else if (path.equals("/register")) {
            // Process registration request
            String username = request.getParameter("newUsername");
            String password = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String firstName = request.getParameter("firstName");
            String email = request.getParameter("email");

            if (password.equals(confirmPassword)) {
                User newUser = new User(username, password, firstName, email);
                if (userDAO.createUser(newUser)) {
                    request.setAttribute("message", "Inscription réussie. Vous pouvez maintenant vous connecter.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "L'inscription a échoué. Veuillez réessayer.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Les mots de passe ne correspondent pas.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    }
}

