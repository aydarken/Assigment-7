package servlets;

import db_connection.ConnectionToDB;
import models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/writePost")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=(String) request.getSession(false).getAttribute("username");

        Post post = new Post();
        post.setText(request.getParameter("content"));
        post.setPublisherUsername(username);
        int result = ConnectionToDB.createPost(post);

        if(result!=0)
        {
            request.setAttribute("message", "Your post "+post.getPublisherUsername()+" published!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("errMessage", "Something went wrong. Post was not published!");
            request.getRequestDispatcher("/writePost.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}