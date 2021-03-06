<%@ page language="java" import="model.*" import="java.util.Vector" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	    <div class="content container">
	    	<div class="row">
	    		<div class="col-md-8">
			    <% Vector<TopicModel> topics = (Vector<TopicModel>) request.getAttribute("topics"); %>
			    <%for(int i=0; i<topics.size(); ++i){ %>
			    <div class="post">
			    	<div class="date">
			    		<%=topics.get(i).pubDate%>
			    	</div>
			    	<h1><a href="/httpDemo2/topic_detail?pk=<%= topics.get(i).id %>"><%=topics.get(i).title %></a></h1><br>
			    	<% if (myCookie2 != null && "2".equals(myCookie2.getValue())) { %><a href="/httpDemo2/topic_delete?pk=<%= topics.get(i).id %>">Удалить</a><%} %>
			    </div>
			    <%} %>
	    		</div>
	    	</div>
		</div>

</body>
</html>