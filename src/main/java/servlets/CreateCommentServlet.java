package servlets;

import db_connection.ConnectionToDB;
import models.Comments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/writeComment")
public class CreateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=(String) request.getSession(false).getAttribute("username");

        Comments comment = new Comments();
        comment.setText(request.getParameter("content"));
        comment.setPublisherUsername(username);
        int pId=Integer.parseInt(request.getParameter("pId"));
        comment.setPostID(pId);
        int result = ConnectionToDB.createComment(comment);

        response.sendRedirect("/post?pId="+pId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}