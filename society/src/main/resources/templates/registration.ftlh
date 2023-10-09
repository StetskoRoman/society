<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    Add new user
<#--    кривовато сделано тут-->
    <@l.loginMacro "/registration"/>
    ${message!}
</@c.page>


