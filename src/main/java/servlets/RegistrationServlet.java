package servlets;

import db_connection.ConnectionToDB;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        int userRegistered = 0;
        try {
            userRegistered = ConnectionToDB.createUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(userRegistered!=0)
        {
            request.setAttribute("message", "You were registered successfully! "+user.getUsername()+", please log in.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}