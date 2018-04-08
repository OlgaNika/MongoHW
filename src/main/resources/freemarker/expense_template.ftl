<!DOCTYPE html>
<html>
<head>
    <title>Expences</title>
</head>
<body>

<#if username??>
    Welcome ${username} <a href="/logout">Logout</a> | <a href="/newexpense">New Expense</a>

    <p>
</#if>

<h1>My Reports</h1>

<#if expenses?has_content>
  <#list expenses as expense>
      Type: ${expense["type"]}
      Amount: ${expense["amount"]}
      Date: ${expense["date"]}
      <hr>
      <p>

  </#list>
<#else>
  <h2>There are no expenses yet. Please add a expense!</h2>
</#if>
</body>
</html>