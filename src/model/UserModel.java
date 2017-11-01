package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserModel {
	
	public int id;
	public String username;
	public String pass;
	public String phone;
	public String email;
	public boolean admin;
	public String fio;
	
	
	public void getUserByPk(int pk){

		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE id="+pk);
                rs.next();
                id = pk;
                username = rs.getString("username");
                pass = rs.getString("pass");
                phone= rs.getString("phone");
                email = rs.getString("email");
                admin = rs.getBoolean("admin");
                fio = rs.getString("fio");
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }		 
	        
	}
	
	public void save() throws SQLException, ClassNotFoundException{
		//try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/httpDemo2";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            //try {
                Statement stmt = con.createStatement();
                String q = new String();
                q+="INSERT INTO \"user\" (username, pass, phone, email, admin, fio) VALUES ";
                q+="('" + username + "','" + pass+ "','"+phone+"','"+email+"','"+admin+"','"+fio+ "')";
                stmt.execute(q);
 
                stmt.close();
            //}
            //finally {
                con.close();
            //}
        //} catch (Exception e) {
           // e.printStackTrace();
        //}		 
	}
	
	public static void deleteUserByUsername(String username) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE username='"+username+"'");
            rs.next();
            int user_id = rs.getInt("id");
            rs.close();
            
            stmt.execute("DELETE FROM \"message\" WHERE user_id='"+user_id+"'");
            stmt.execute("DELETE FROM \"topic\" WHERE user_id='"+user_id+"'");
            stmt.execute("DELETE FROM \"user\" WHERE username='"+username+"'");
            
            stmt.close();
            

            con.close();
		
	}
	
	public static int auth(String username, String pass) throws SQLException, ClassNotFoundException{
		int res = 0; // ошибка авторизации
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/httpDemo2";
        String login = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE username='"+username+"'");
            rs.next();
            String usernameDB = rs.getString("username");
            String passDB = rs.getString("pass");
            boolean admin = rs.getBoolean("admin");
            if (!passDB.equals(pass)  || !usernameDB.equals(username)){ throw new SQLException("Неверная пара логин/пароль");}
            rs.close();

            stmt.close();

            con.close();
            if (admin){res=2;}else // admin
            res=1;// обычный юзер
            
            return res;
            
	}
}
