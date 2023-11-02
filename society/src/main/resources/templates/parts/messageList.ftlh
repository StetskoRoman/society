
<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>
<#--16 здесь  id="message-list"> добавили -->
<div class="card-columns" id="message-list">

<#--  page.content - имя филда       -->
    <#list page.content as message>
        <div class="card my-3" data.id="${message.id}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
<#--                перейти на сообщения конкретного пользователя, будет ссылка с именем автора-->
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
<#--                отображаться только для текущего пользователя    -->
                <#if message.author.id == currentUserId>
                    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        No message
    </#list>

</div>






<#--<div class="card-columns">-->
<#--    <#list messages as message>-->
<#--        <div class="card my-3">-->
<#--            <#if message.filename??>-->
<#--                <img src="/img/${message.filename}" class="card-img-top">-->
<#--            </#if>-->
<#--            <div class="m-2">-->
<#--                <span>${message.text}</span>-->
<#--                <i>${message.tag}</i>-->
<#--            </div>-->
<#--            <div class="card-footer text-muted">-->
<#--                ${message.authorName}-->
<#--            </div>-->
<#--        </div>-->
<#--    <#else>-->
<#--        No message-->
<#--    </#list>-->
<#--</div>-->