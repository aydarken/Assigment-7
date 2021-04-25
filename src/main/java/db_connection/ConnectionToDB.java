package db_connection;

import models.Comments;
import models.Post;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ConnectionToDB {
    private static final String url = "jdbc:mysql://localhost:3306/discussion forum?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    static Connection connection;
    static Statement statement;

    public static void connect() throws SQLException, ClassNotFoundException {
        if(connection==null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static User getUser(String username, String password) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM users WHERE username='" + username +"' AND "+"password='" + password+"'";
        User user = new User();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getUserById(int userId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM users WHERE user_id=" + userId;
        User user = new User();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static int createUser(User user) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "insert into users (email, username, password) values('"
                +user.getEmail()+"', '"
                +user.getUsername()+"', '"
                +user.getPassword()+"')";

        int result=0;
        try{
            result = statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static List<User> getUsers() throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM users";
        List<User> userList = new ArrayList<User>();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                User user=new User();
                user.setId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }


    public static List<Post> getPosts() throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<Post>();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                Post post =new Post();
                post.setId(resultSet.getInt("pId"));
                post.setText(resultSet.getString("pContent"));
                post.setPublisherUsername(resultSet.getString("username"));
                posts.add(post);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }


    public static List<Comments> getComments(long pId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM comments where pid="+pId;
        List<Comments> comments = new ArrayList<Comments>();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                Comments comment =new Comments();
                comment.setId(resultSet.getInt("cId"));
                comment.setText(resultSet.getString("content"));
                comment.setPostID(resultSet.getInt("pId"));
                comment.setPublisherUsername(resultSet.getString("username"));;
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

    public static Post getPost(int pId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT * FROM posts where pId="+pId;
        Post post =new Post();
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {

                post.setId(resultSet.getInt("pId"));
                post.setText(resultSet.getString("pContent"));
                post.setPublisherUsername(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return post;
    }

    public static int getPostLikes(int pId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT count(*) as likes FROM post_likes where type='like' pid="+pId;
        int likes=0;
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                likes = resultSet.getInt("likes");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return likes;
    }

    public static int getPostDislikes(int pId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT count(*) as dislike FROM post_likes where type='dislike' pid="+pId;
        int dislike=0;
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                dislike = resultSet.getInt("dislike");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dislike;
    }

    public static int getCommentLikes(int cId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT count(*) as likes FROM comment_likes where type='like' cId="+cId;
        int likes=0;
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                likes = resultSet.getInt("likes");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return likes;
    }

    public static int getCommentDislikes(int cId) throws SQLException, ClassNotFoundException {
        connect();

        String sqlString = "SELECT count(*) as dislike FROM comment_likes where type='dislike' cId="+cId;
        int dislike=0;
        try {
            ResultSet resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                dislike = resultSet.getInt("dislike");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dislike;
    }


    public static int createPost(Post post){

        String sqlString = "INSERT INTO `forum`.`posts` (`pContent`, `username`) values('"
                +post.getText()+"', '"
                +post.getPublisherUsername()+"')";

        int result=0;
        try{
            result = statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int createComment(Comments comment){

        String sqlString = "INSERT INTO `forum`.`comments` (`content`, `pId`, `username`) values('"
                +comment.getText()+"', "
                +comment.getPostID()+", '"
                +comment.getPublisherUsername()+"')";

        int result=0;
        try{
            result = statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static int updatePost(Post post){

        String sqlString = "UPDATE `forum`.`posts` SET" +
                " pContent = '"+post.getText()+"'" +
                " WHERE pId = "+post.getId()+"";

        int result=0;
        try{
            result = statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
