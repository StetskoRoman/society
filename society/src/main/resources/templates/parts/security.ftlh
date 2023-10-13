<#assign
known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
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



