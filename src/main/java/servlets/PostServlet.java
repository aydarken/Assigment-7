package servlets;

import java.util.List;
import db_connection.ConnectionToDB;
import models.Comments;
import models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/post")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            username = (String) request.getSession(false).getAttribute("username");
        }

        int pId = Integer.parseInt(request.getParameter("pId"));
        Post post = null;
        try {
            post = ConnectionToDB.getPost(pId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("userPost", false);
        if (username != null) {
            if (username.equals(post.getPublisherUsername())) {
                request.setAttribute("userPost", true);
            }
        }
        List<Comments> postComments = null;
        try {
            postComments = ConnectionToDB.getComments(pId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int likes = 0;
        try {
            likes = ConnectionToDB.getPostLikes(pId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int dislikes = 0;
        try {
            dislikes = ConnectionToDB.getPostDislikes(pId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("post", post);
        request.setAttribute("comments", postComments);
        request.setAttribute("likes", likes);
        request.setAttribute("dislikes", dislikes);

        request.getRequestDispatcher("/post.jsp").forward(request, response);
    }

}