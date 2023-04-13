<%
   String message = pageContext.getException().getMessage();
   String exception = pageContext.getException().getClass().toString();
%>
<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
<h2>Ooops....Something went wrong....Details:</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
<p></a></p>
<p><a href="./index.jsp">Return to the start page</a></p>
<p><a href="./registration.jsp">Return to the registration page</a></p>
</body>
</html>