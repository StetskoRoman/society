<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    ${message!""}
    <@l.login "/login" false/>
</@c.page>





<#--старый логин-->
<#--<#import "parts/common.ftlh" as c>-->
<#--<#import "parts/loginMacro.ftlh" as l>-->

<#--<@c.page>-->
<#--    Login page-->
<#--&lt;#&ndash;    здесь тот параметр прописали который был в макросе loginMacro.ftlh&ndash;&gt;-->
<#--    <@l.loginMacro "/login" />-->
<#--    <a href="/registration">Add new user</a>-->
<#--</@c.page>-->



