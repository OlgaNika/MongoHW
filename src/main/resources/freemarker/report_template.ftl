<!DOCTYPE html>
<html>
<head>
    <title>My Reports</title>
</head>
<body>

<#if username??>
    Welcome ${username} <a href="/logout">Logout</a> | <a href="/newreport">New Report</a>

    <p>
</#if>

<h1>My Reports</h1>

<#if reports?has_content>
  <#list reports as report>
      Status ${report["status"]} <a href="/expensies/${report["id"]}">Name ${report["name"]}</a><br>
      total: ${report["total"]}
      Report id : ${report["id"]}
      <hr>
      <p>


  </#list>
<#else>
  <h2>There are no reports yet. Please add a report!</h2>
</#if>
</body>
</html>