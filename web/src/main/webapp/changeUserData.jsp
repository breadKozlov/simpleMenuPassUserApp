<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Data</title>
</head>
<body>
<form action="change" method="POST">
    <input type="hidden" name="login" value= <%= request.getParameter("login") %>>
    Name: <input type="text" required name="name" value="${name}" />
    <br><br>
    Age: <input type="number" required step="1" min="10" max="100" value="${age}" name="age" />
    <br><br>
    Email: <input type="email" required placeholder="user@gmail.com" name="email" value="${email}"/>
    <br><br>
    Password: <input type="password" required name="password" value="${password}"/>
    <br><br>
    <input type="submit" value="Change data" />
    <br><br>
    <p><a href="./user?login=${login}">Return to the user account page</a></p>
</form>
</body>
</html>