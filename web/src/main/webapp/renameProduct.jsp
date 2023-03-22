<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
<form action="renameProduct">
    <input type="hidden" name="id" value= <%= request.getParameter("id") %>>
    Name: <input name="name" />
    <br><br>
    Description: <input name="description" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>