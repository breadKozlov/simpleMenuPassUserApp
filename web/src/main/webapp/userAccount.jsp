<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>User Info</title>
    </head>
    <body>
        <p>Name: ${name}</p>
        <p>Age: <%= request.getParameter("age") %></p>
        <p>Email: <%= request.getParameter("email") %></p>
        <p>Login: <%= request.getParameter("login") %></p>
        <p>Password: <%= request.getParameter("password") %></p>
        <p><a href="./menu.jsp">Go to menu</a></p>
    </body>
</html>