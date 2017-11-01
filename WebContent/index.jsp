<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <title>Форум</title>
            <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
            <link rel="stylesheet" type="text/css" href="css/blog.css">
        </head>
        <body>
	    <div class="page-header">
	        <h1><a href="/httpDemo2">Форум</a></h1>
	    </div>
		<div class="content">
			<br>
			<%
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
				}				
			%>
			<% if (myCookie1 == null) { %>
			Вы вошли как: Гость
			<% } else { %>
			Вы вошли как: <%=myCookie1.getValue()%>
			<% } %>
			<br>
			<h1>Выберите действие:</h1>
			<a href="/httpDemo2/topic_list">Список тем</a><br>
			<% if (myCookie1 == null) { %><a href="/httpDemo2/auth">Вход</a><br><%} %>
			<% if (myCookie1 != null) { %><a href="/httpDemo2/exit">Выйти</a><br><%} %>
			<% if (myCookie2 != null && "2".equals(myCookie2.getValue())) { %><a href="/httpDemo2/user_create_form">Добавить пользователя</a><br><%} %>
			<% if (myCookie2 != null && "2".equals(myCookie2.getValue())) { %><a href="/httpDemo2/user_delete_form">Удалить пользователя</a><br><%} %>
			<% if (myCookie2 != null && "2".equals(myCookie2.getValue())) { %><a href="/httpDemo2/topic_create_form">Добавить тему</a><br><%} %>
			Для регистрации обратитесь к администраторам
		</div>
		
	
        </body>
    </html>