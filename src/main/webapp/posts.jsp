<%@ page import="db_connection.ConnectionToDB" %>
<%@ page import="models.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="message"><%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%></p>

<h2>All Posts</h2>
<%
    if(session.getAttribute("username")!=null){
%>
<button><a href="writePost.jsp">Write your own post</a></button>
<%}
    List<Post> posts = ConnectionToDB.getPosts();
    if(posts!=null){
        for(Post post : posts){
            long commentsNum = ConnectionToDB.getComments((int) post.getId()).size();
%>
<div class="post">
    <h4><%=post.getPublisherUsername()%></h4>
    <h3><a href="post?pId=<%=post.getId()%>"></a></h3>
    <p><%=post.getText()%></p>
    <p><%=commentsNum%> comments</p>

    <%
        if(session.getAttribute("username")!=null){
            if(session.getAttribute("username").equals(post.getPublisherUsername())){
    %>
    <button><a href="editPost.jsp?pId=<%=post.getId()%>">Edit</a></button>
    <%
            }
        }
    %>
</div>

<%
        }
    }
%>