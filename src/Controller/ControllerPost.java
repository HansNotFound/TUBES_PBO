/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comment;
import Model.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HansNotFound
 */
public class ControllerPost {

    static DatabaseHandler conn = new DatabaseHandler();

    public static ArrayList<Post> getAllPost() {

        ArrayList<Post> posts = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM postingan";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("IdPostingan"));
                post.setUsername_user(rs.getString("Username"));
                post.setCaption(rs.getString("Caption"));
                post.setPostNickname(rs.getString("PostNickname"));
                post.setJumlahLike(rs.getInt("Likes"));
                post.setWaktuPost(rs.getString("WaktuPost"));
                post.setImagepath(rs.getString("GambarPost"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (posts);
    }

    public static ArrayList<Post> getListPostByUser(String Username) {
        conn.connect();
        ArrayList<Post> listPost = new ArrayList<>();
        String query = "SELECT * FROM postingan WHERE Username='" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("IdPostingan"));
                post.setUsername_user(rs.getString("Username"));
                post.setCaption(rs.getString("Caption"));
                post.setPostNickname(rs.getString("PostNickname"));
                post.setJumlahLike(rs.getInt("Likes"));
                post.setWaktuPost(rs.getString("WaktuPost"));
                post.setImagepath(rs.getString("GambarPost"));
                listPost.add(post);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPost;
    }

    public static boolean deleteKoneksiComment(int idPost) {
        conn.connect();

        String query = "DELETE FROM comment WHERE idPostingan='" + idPost + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean deleteKoneksiLike(int idPost) {
        conn.connect();

        String query = "DELETE FROM liker WHERE idPostingan='" + idPost + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean deletePost(int idPost) {
        conn.connect();
        if (deleteKoneksiComment(idPost)) {
            if (deleteKoneksiLike(idPost)) {
                String query = "DELETE FROM postingan WHERE idPostingan='" + idPost + "'";
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query);
                    return (true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return (false);
                }
            }
        }
        return false;
    }

    public static boolean insertNewPost(Post post) {
        conn.connect();
        String query_InsertNewPost = "INSERT INTO postingan VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertNewPost);
            stmt.setInt(1, post.getIdPost());
            stmt.setString(2, post.getUsername_user());
            stmt.setString(3, post.getCaption());
            stmt.setString(4, post.getImagepath());
            stmt.setInt(5, post.getJumlahLike());
            stmt.setString(6, post.getWaktuPost());
            stmt.setString(7, post.getPostNickname());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateLikePost(Post post, int idPost) {
        conn.connect();
        String query = "UPDATE postingan SET Likes='" + post.getJumlahLike() + "' WHERE IdPostingan='" + idPost + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //get 1 post doang
    public static Post getPost(int idPost) {
        Post post = new Post();
        conn.connect();
        String query = "SELECT * FROM postingan WHERE idPostingan = "+ idPost;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                post.setIdPost(rs.getInt("IdPostingan"));
                post.setUsername_user(rs.getString("Username"));
                post.setCaption(rs.getString("Caption"));
                post.setPostNickname(rs.getString("PostNickname"));
                post.setJumlahLike(rs.getInt("Likes"));
                post.setWaktuPost(rs.getString("WaktuPost"));
                post.setImagepath(rs.getString("GambarPost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (post);
    }
    
}
