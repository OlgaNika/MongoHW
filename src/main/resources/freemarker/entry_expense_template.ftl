<!doctype HTML>
<html
<head>
    <title>
        Blog Post
    </title>
</head>
<body bgcolor = #FFFF99>
<#if username??>
    Welcome ${username} <a href="/logout">Logout</a> | <a href="/newpost">New Post   </a><br>
                         <a href="/">Blog Home</a><br>

     <p>
</#if>






<h2>${post["title"]}</h2>
Posted ${post["date"]?datetime}<i> By ${post["author"]}</i><br>

Description   ${post["description"]}


<br>

${post["body"]}
<p>
    <em>Filed Under</em>:
    <#if post["tags"]??>
        <#list post["tags"] as tag>
            ${tag}
        </#list>
    </#if>
<p>



</ul>
</body>
</html>



