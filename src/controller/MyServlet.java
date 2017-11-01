package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import java.util.Vector;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name="/MyServlet",
urlPatterns = {"/auth",  "/topic_list", "/topic_detail",
		"/user_create_form", "/topic_create_form","/user_delete_form", "/exit", "/topic_delete", "/message_create_form", "/message_alter_form","/message_delete"})
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");
        String userPath=request.getServletPath();
        if ("/auth".equals(userPath)){
        	
        }else
        if("/topic_list".equals(userPath)){

        	Vector<TopicModel> topics = new Vector<TopicModel>();
        	topics = TopicModel.all();
        	request.setAttribute("topics", topics);
        	
        }else
        if("/topic_detail".equals(userPath)){
        	int topicPk = Integer.parseInt(request.getParameter("pk"));
        	TopicModel topic = new TopicModel();
        	topic.getTopicByPk(topicPk);
        	request.setAttribute("topic", topic);
        	Vector<MessageModel> messages = new Vector<MessageModel>();
        	messages = MessageModel.allForTopic(topicPk);
        	request.setAttribute("messages", messages);
        }else
            if("/exit".equals(userPath)){
				String cookieName1 = "username";
				String cookieName2 = "admin";
				Cookie cookies [] = request.getCookies ();
				Cookie myCookie1 = null;
				Cookie myCookie2 = null;
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals (cookieName1)) {
								myCookie1 = cookies[i];
							}
						else {
							if (cookies[i].getName().equals (cookieName2)) {
								myCookie2 = cookies[i];
							}
						}
					}
				myCookie1.setMaxAge(0);
				myCookie2.setMaxAge(0);
				response.addCookie(myCookie1);
				response.addCookie(myCookie2);
				}
            }else
            if ("/topic_delete".equals(userPath)){
            	try{
	            	int pk = Integer.parseInt(request.getParameter("pk"));
	            	TopicModel.deleteTopicByPk(pk);
	            	request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
            	}
            	catch (Exception e){
            		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
            	}
            	return;
            }else
                if("/message_delete".equals(userPath)){
                	try{

        				MessageModel.deleteMessageByPk(Integer.parseInt(request.getParameter("pk")));
        				request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
                	}
                	catch (Exception e){
                		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
                	} 
                	return;
                }
        
        request.getRequestDispatcher("/WEB-INF/views"+userPath+".jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String userPath=request.getServletPath();
		request.setCharacterEncoding("utf-8");
        if ("/user_create_form".equals(userPath)){
        	try{
	        	UserModel user = new UserModel();
	        	user.username = request.getParameter("username");
	        	user.pass = request.getParameter("pass");
	        	user.phone = request.getParameter("phone");
	        	user.email = request.getParameter("email");
	        	user.admin = Boolean.parseBoolean(request.getParameter("admin"));
	        	user.fio = request.getParameter("fio");
	        	user.save();
	        	request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
        	}
        	catch (Exception e){
        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
        	}
        }else 
        if ("/user_delete_form".equals(userPath)){
        	try{
	        	String username = request.getParameter("username");
	        	UserModel.deleteUserByUsername(username);
	        	request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
        	}
        	catch (Exception e){
        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
        	}      	
        } else
        if ("/auth".equals(userPath)){
        	try{
	        	String username = request.getParameter("username");
	        	String pass = request.getParameter("pass");
	        	String admin = "0";
	        	
	        	switch (UserModel.auth(username, pass)){
	        	case 0:
	        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
	        		return;
	        	case 1:
	        		admin = "1";
	        		break;
	        	case 2:
	        		admin = "2";
	        		break;
	        	}
	        		Cookie cookie1 = new Cookie ("username",username);
	        		Cookie cookie2 = new Cookie ("admin",admin);
	        		cookie1.setMaxAge(60*60);
	        		cookie2.setMaxAge(60*60);
	        		response.addCookie(cookie1);
	        		response.addCookie(cookie2);
	        		request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
        	}
        	catch (Exception e){
        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
        	}          	
        	
        }else
        if ("/topic_create_form".equals(userPath)){
        	try{
				String cookieName1 = "username";
				Cookie cookies [] = request.getCookies ();
				Cookie myCookie1 = null;
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals (cookieName1)) {
								myCookie1 = cookies[i];
							}
					}
				}
				TopicModel topic = new TopicModel();
				topic.title = request.getParameter("title");
				topic.content = request.getParameter("content");
				topic.save(myCookie1.getValue());
				//request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
				response.sendRedirect("http://127.0.0.1:8080/httpDemo2/topic_list");
        	}
        	catch (Exception e){
        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
        	}       
        }else
        if("/message_create_form".equals(userPath)){
        	try{
				String cookieName1 = "username";
				Cookie cookies [] = request.getCookies ();
				Cookie myCookie1 = null;
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals (cookieName1)) {
								myCookie1 = cookies[i];
							}
					}
				}
				MessageModel msg = new MessageModel();
				msg.content = request.getParameter("content");
				msg.save(myCookie1.getValue(), Integer.parseInt(request.getParameter("topic_id")));
				response.sendRedirect("http://127.0.0.1:8080/httpDemo2/topic_detail?pk="+request.getParameter("topic_id"));
        	}
        	catch (Exception e){
        		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
        	} 
        }else
            if("/message_alter_form".equals(userPath)){
            	try{
    
    				MessageModel.alterMessageByPk(Integer.parseInt(request.getParameter("id")), request.getParameter("content"));
    				request.getRequestDispatcher("/WEB-INF/views"+"/success"+".jsp").forward(request, response);
            	}
            	catch (Exception e){
            		request.getRequestDispatcher("/WEB-INF/views"+"/error"+".jsp").forward(request, response);
            	} 
            }
        
        	
	}

}
