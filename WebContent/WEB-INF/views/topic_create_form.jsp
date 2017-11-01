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
			<form method="POST" action="/httpDemo2/topic_create_form">
				 <p><b>Введите данные</b></p>
				 Title: <br><input type="text" name="title"><br><br>
				 Content:<br> <input type="text" name="content"><br><br>
				 <input type="submit">
			</form>
		</div>
		
	
        </body>
    </html>