<%@ page import="models.Post" %>
<%@ page import="db_connection.ConnectionToDB" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Comments" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Post post = (Post) request.getAttribute("post");
    int likes = (int) request.getAttribute("likes");
    int dislikes = (int) request.getAttribute("dislikes");
%>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title><%=post.getPublisherUsername()%></title>
    </head>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="post">
    <h4><%=post.getPublisherUsername()%></h4>
    <p><%=post.getText()%></p>
    <p><span><%=likes%> likes</span>/<span><%=dislikes%> dislikes</span></p>
    <%
        List<Comments> comments = (List<Comments>) request.getAttribute("comments");
        if(comments!=null){
            for(Comments comment : comments){
                long clikes = ConnectionToDB.getCommentLikes(comment.getId());
                long cdislikes = ConnectionToDB.getCommentDislikes(comment.getId());
    %>
    <div class="comment">
        <h4><%=comment.getPublisherUsername()%></h4>
        <p><%=comment.getText()%></p>
        <p><span><%=clikes%> likes</span>/<span><%=cdislikes%> dislikes</span></p>
    </div>
    <%
            }
        }
    %>
    <%
        if(session.getAttribute("username")!=null){
    %>
    <form action="/writeComment" method="POST">
        <textarea name="content"></textarea>
        <input type="hidden" name="pId" value="<%=post.getId()%>">
        <button>Write</button>
    </form>
    <%
        if(session.getAttribute("username").equals(post.getPublisherUsername())){
    %>
    <button><a href="editPost.jsp?pId=<%=post.getId()%>">Edit</a></button>
    <%
            }
        }
    %>
</div>
</body>
</html>