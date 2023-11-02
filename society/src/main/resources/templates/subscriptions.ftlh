<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>${userChannel.username}</h3>
    <div>${type}</div>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a href="/user-messages/${user.id}">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>


<#--<#import "parts/common.ftlh" as c>-->

<#--<@c.page>-->
<#--    <h3>${userChannel.username}</h3>-->
<#--&lt;#&ndash;    для подписки приписка type &ndash;&gt;-->
<#--    <div>${type}</div>-->
<#--    <ul class="list-group">-->
<#--        <#list users as user>-->
<#--            <li class="list-group-item">-->
<#--&lt;#&ndash;                ccылка на страницу пользователя&ndash;&gt;-->
<#--                <a href="/user-messages/${user.id}">${user.getUsername()}</a>-->
<#--            </li>-->
<#--        </#list>-->
<#--    </ul>-->
<#--</@c.page>-->