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
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center"
                       href="/user-messages/${message.author.id}">${message.authorName}</a>
                    <a class="col align-self-center" href="/messages/${message.id}/like">
                        <#if message.meLiked>
                            <i class="fas fa-heart"></i>
                        <#else>
<#--                            пустое сердце-->
                            <i class="far fa-heart"></i>
                        </#if>
                        ${message.likes}
                    </a>
                    <#if message.author.id == currentUserId>
                        <a class="col btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                            Edit
                        </a>
                    </#if>
                </div>
            </div>

        </div>
    <#else>
        No message
    </#list>

</div>


<#--&lt;#&ndash;                перейти на сообщения конкретного пользователя, будет ссылка с именем автора&ndash;&gt;-->
<#--<a href="/user-messages/${message.author.id}">${message.authorName}</a>-->
<#--&lt;#&ndash;                отображаться только для текущего пользователя    &ndash;&gt;-->
<#--<#if message.author.id == currentUserId>-->
<#--    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">-->
<#--        Edit-->
<#--    </a>-->
<#--</#if>-->
<#--</div>-->



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