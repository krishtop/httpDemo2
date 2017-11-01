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
			<form method="POST" action="/httpDemo2/user_create_form">
				 <p><b>Введите данные</b></p>
				 Username: <br><input type="text" name="username"><br><br>
				 Password:<br> <input type="password" name="pass"><br><br>
				 Phone:  <br>  <input type="text" name="phone"><br><br>
				 E-mail:  <br> <input type="text" name="email"><br><br>
				 Admin:    <input type="checkbox" name="admin"><br><br>
				 FIO:   <br>   <input type="text" name="fio"><br><br>
				 <input type="submit">
			</form>
		</div>
		
	
        </body>
    </html>