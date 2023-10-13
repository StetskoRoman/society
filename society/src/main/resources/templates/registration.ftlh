<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    Add new user
<#--    кривовато сделано тут, регистрация так то отдельно должна быть а не по форме логина-->
    <@l.loginMacro "/registration"/>
    ${message!}
</@c.page>


