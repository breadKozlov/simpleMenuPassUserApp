<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
<form action="registration" method="POST">
    Name: <input type="text" required name="name" />
    <br><br>
    Age: <input type="number" required step="1" min="10" max="100" value="10" name="age" />
    <br><br>
    Email: <input type="email" required placeholder="user@gmail.com" name="email" />
    <br><br>
    Login: <input name="login" required />
    <br><br>
    Password: <input type="password" required name="password" />
    <br><br>
    <input type="submit" value="Register" />
    <br><br>
    <p><a href="./index.jsp">Return to the start page</a></p>
</form>
</body>
</html>