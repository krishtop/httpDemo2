package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class MessageModel {
	
	public int id;
	public int topicId;
	public int userId;
	public String pubDate;
	public String content;
	
	
	public void getMessageByPk(int pk){

		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"message\" WHERE id="+pk);
                rs.next();
                id = pk;
                topicId = rs.getInt("topic_id");
                userId = rs.getInt("user_id");
                content = rs.getString("content");
                pubDate= rs.getString("pub_date");
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }		 
	        
	}
	
	public static Vector<MessageModel> allForTopic(int topicPk){

		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            Vector<MessageModel> vec = new Vector<MessageModel>();
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM message WHERE topic_id="+topicPk+ " ORDER BY pub_date");
                while(rs.next()){
                	MessageModel msg = new MessageModel();
	                msg.id = rs.getInt("id");
	                msg.topicId = rs.getInt("topic_id");
	                msg.userId = rs.getInt("user_id");
	                msg.content = rs.getString("content");
	                msg.pubDate= rs.getString("pub_date");
	                vec.addElement(msg);
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
	
	public void save(String username, int topId) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE username='"+username+"'");
            rs.next();
            userId = rs.getInt("id");
            

            
            topicId = topId;
            rs.close();
            
            String q = new String();
            
            q+="INSERT INTO \"message\" (content, user_id, topic_id) VALUES ";
            q+="('" + content + "','"+userId+"','"+topicId+ "')";
            stmt.execute(q);

            stmt.close();
            con.close();
	}
	
	public static void deleteMessageByPk(int pk) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();


            
            stmt.execute("DELETE FROM \"message\" WHERE id='"+pk+"'");

            
            stmt.close();
            

            con.close();
	}
	
	public static void alterMessageByPk(int pk, String newContent) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();


            
            stmt.execute("UPDATE \"message\" SET content = '"+newContent+"' WHERE id='"+pk+"'");

            
            stmt.close();
            

            con.close();
	}
	
}
