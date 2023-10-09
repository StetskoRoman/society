<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
    </div>

    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Write message"/><br>
            <input type="text" name="tag" placeholder="Tag@">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add message</button>
        </form>
    </div>
    <div>List of messages</div>
    <form method="get" action="/main">
<#--        value="${filter!}"> означает что переменной фильтр может и не быть!-->
        <input type="text" name="filter" value="${filter!}">
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
        <button type="submit">Find</button>
    </form>
<#--    в цикле обход сообщений-->
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else> No messages
    </#list>

</@c.page>