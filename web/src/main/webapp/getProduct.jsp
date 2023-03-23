<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Form</title>
</head>
<body>
<h2>Current user: <%= request.getParameter("login") %></h2>
<form action="getProduct">
    <input type="hidden" name="login" value= <%= request.getParameter("login") %>>
    Enter Id of product: <input type="number" name="id" step="1" min="1" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>