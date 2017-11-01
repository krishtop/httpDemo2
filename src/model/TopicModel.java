package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TopicModel {

	public int id;
	public String content;
	public String title;
	public String pubDate;
	public int userId;
	
	public void getTopicByPk(int pk){

		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"topic\" WHERE id="+pk);
                rs.next();
                id = pk;
                content = rs.getString("content");
                title = rs.getString("title");
                pubDate= rs.getString("pub_date");
                userId = rs.getInt("user_id");
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }		 
	        
	}
	
	public static Vector<TopicModel> all(){

		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            Vector<TopicModel> vec = new Vector<TopicModel>();
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM topic ORDER BY pub_date DESC");
                while(rs.next()){
                	TopicModel topic = new TopicModel();
	                topic.id = rs.getInt("id");
	                topic.content = rs.getString("content");
	                topic.title = rs.getString("title");
	                topic.pubDate= rs.getString("pub_date");
	                topic.userId = rs.getInt("user_id");
	                vec.addElement(topic);
                }
                rs.close();
                stmt.close();
            } 
            finally {
                con.close();
            }

    		return vec;
        }
		catch (Exception e) {
            e.printStackTrace();
        }
		return null;	
      
	}
	
	public void save(String username) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE username='"+username+"'");
            rs.next();
            userId = rs.getInt("id");
            
            rs.close();
            
            String q = new String();
            
            q+="INSERT INTO \"topic\" (title, content, user_id) VALUES ";
            q+="('" + title + "','"+content+"','"+userId+ "')";
            stmt.execute(q);

            stmt.close();
            con.close();

	}
	
	public static void deleteTopicByPk(int pk) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();


            
            stmt.execute("DELETE FROM \"message\" WHERE topic_id='"+pk+"'");
            stmt.execute("DELETE FROM \"topic\" WHERE id='"+pk+"'");
            
            stmt.close();
            

            con.close();		
	}

	
}
