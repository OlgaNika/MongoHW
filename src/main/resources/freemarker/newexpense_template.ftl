<!doctype HTML>
<html>
<head>
    <title>Create a new Expense </title>
</head>
<body  	bgcolor = #FFFF99>
<#if username??>
    Welcome ${username} <a href="/logout">Logout</a> | <a href="/">Blog Home</a>

    <p>
</#if>
<form action="/newexpense" method="POST">
    ${errors!""}


<table border="1">
   <caption>Новая запись expense </caption>
   <tr>
    <th>Дата </th>
    <th>Сумма </th>
    <th>Категория </th>
    <th>Комментарии</th>

   </tr>
<tr>
<td><input  name="subject" size="30" value="${subject!""}"></td>
<td><input type="text" name="body" size="30"  value="${body!""}" ${body!""}></td>
<td><input type="text" name="tags" size="30" value="${tags!""}"></td>
<td><input type="text" name="description" size="60" value="${description!""}"></td>
</tr>
</table>

<input type="submit" value="Submit">

</body>
</html>

