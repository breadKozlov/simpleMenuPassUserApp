
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>User Info</title>
    </head>
    <body>
        <h2>Current user: ${login}</h2>
          <p><a href="./getProduct.jsp?login=${login}">Get product</a></p>
          <p><a href="./addProduct.jsp?login=${login}">Add product</a></p>
          <p><a href="./products?login=${login}">Show all products</a></p>
          <p><a href="./user?login=${login}">Go to personal account</a></p>
          <p><a href="./index.jsp">Return to the start page</a></p>
    </body>
</html>