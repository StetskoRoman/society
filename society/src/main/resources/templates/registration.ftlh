<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    ${message!""}
    <@l.login "/registration" true />
</@c.page>





<#--<#import "parts/common.ftlh" as c>-->
<#--<#import "parts/loginMacro.ftlh" as l>-->

<#--<@c.page>-->
<#--    Add new user-->
<#--&lt;#&ndash;    кривовато сделано тут, регистрация так то отдельно должна быть а не по форме логина&ndash;&gt;-->
<#--    ${message!}-->
<#--    <@l.loginMacro "/registration" true/>-->

<#--</@c.page>-->


