<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
<h2>Current user: ${login}</h2>
<form action="renameProduct">
    <input type="hidden" name="id" value= <%= request.getParameter("id") %>>
    <input type="hidden" name="login" value= <%= request.getParameter("login") %>>
    Name: <input name="name" required/>
    <br><br>
    Description: <input name="description" required/>
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>