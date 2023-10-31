<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>${userChannel.username}</h3>
<#--    для подписки приписка type -->
    <div>${type}</div>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
<#--                ccылка на страницу пользователя-->
                <a href="/user-messages/${user.id}">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>