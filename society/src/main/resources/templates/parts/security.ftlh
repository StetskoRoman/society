<#--штука которая будет хранить контекст авторизованного пользователя, который можно будет использовать везде куда просто <#include "security.ftlh">-->
<#assign
known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    <#--        тут описывается юзер только если он авторизован (тот принципал что вставляется в методы из контекста)-->
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    currentUserId = user.getId()
    isAdmin = user.isAdmin()
    >
<#--    cлучай для неавторизованного   currentUserId = -1 ни одному пользователю не соответствует-->
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    currentUserId = -1
    >
</#if>

<#--<#assign-->
<#--&lt;#&ndash;    если объект определен в контексте- значит можно работать с сессией пользователя, &ndash;&gt;-->
<#--known = Session.SPRING_SECURITY_CONTEXT??-->
<#-->-->
<#--&lt;#&ndash;если сессия существует - делаем чето, иначе заглушка, ВСе ЭТО ЧТОБЫ СКРЫТЬ ПАНЕЛЬ РОЛеЙ ДЛЯ ПОЛЬЗОВАТЕЛЕЙ КРОМЕ АДМИНА&ndash;&gt;-->
<#--<#if known>-->
<#--&lt;#&ndash;    значение содержит описание пользователя и можно использовать его методы&ndash;&gt;-->
<#--    <#assign user = Session.SPRING_SECURITY_CONTEXT.authentication.principal-->
<#--    name = user.getUsername()-->
<#--    isAdmin = user.isAdmin()-->
<#--        >-->
<#--    <#else >-->
<#--    <#assign-->
<#--        name = "unknown"-->
<#--        isAdmin = false-->
<#--        >-->

<#--</#if>-->



