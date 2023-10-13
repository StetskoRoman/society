<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user" > User list </a> </span>
    </div>

    <div>
<#--   enctype="multipart/form-data" позволяет файлы передавать     -->
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Write message"/><br>
            <input type="text" name="tag" placeholder="Tag@"><br>
            <input type="file" name="file">
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
            <div>
<#--        <#if message.filename??> здесь ?? - это приведение к типу boolean        -->
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else> No messages
    </#list>

</@c.page>