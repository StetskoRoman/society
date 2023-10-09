<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    Login page
<#--    здесь тот параметр прописали который был в макросе loginMacro.ftlh-->
    <@l.loginMacro "/login" />
    <a href="/registration">Add new user</a>
</@c.page>



