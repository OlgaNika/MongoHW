<!DOCTYPE html>
<html>
<head>
    <title>Expences</title>
</head>
<body bgcolor = #CCFFFF >

<#if username??>
    Welcome ${username} <a href="/logout">Logout</a> | <a href="/newexpense">New Expense</a>

    <p>
</#if>

<h1>My Expenses  </h1>

<#if myexpenses?has_content>
  <#list myexpenses as expenses>
      <h2><a href="/expenses/${expenses["permalink"]}">${expenses["title"]}</a></h2>
      Posted ${expenses["date"]?datetime} <i>By ${expenses["author"]}</i><br>
     Description :   ${expenses["description"]}
      <br>
      Comments:
      <#if expenses["comments"]??>
          <#assign numComments = expenses["comments"]?size>
              <#else>
                  <#assign numComments = 0>
      </#if>

      <a href="/expenses/${expenses["permalink"]}">${numComments}</a>
      <hr>
      ${expenses["body"]!""}
      <p>

      <p>
          <em>Filed Under</em>:
          <#if expenses["tags"]??>
              <#list expenses["tags"] as tag>
                  <a href="/tag/${tag}">${tag}</a>
              </#list>
          </#if>

      <p>
  </#list>
<#else>
  <h2>There are no expenses yet. Please add a expenses!</h2>
</#if>
</body>
</html>








<!--

<#if expenses?has_content>

  <#list expenses as expense>
      Type: ${expense["type"]}
     // Amount: ${expense["amount"]}
      Date: ${expense["date"]}
      <hr>
      <p>

  </#list>
<#else>
  <h2>There are no expenses yet. Please add a expense!</h2>
</#if>
</body>
</html>
-->