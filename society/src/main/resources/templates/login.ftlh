<#import "parts/common.ftlh" as c>
<#import "parts/loginMacro.ftlh" as l>

<@c.page>
<#--    ошибка с загрузкой логина-->
    <#if Session?? && SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
<#--    Ошибка с активацией-->
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
<#--    false это параметр  isRegisterForm и он фолс так как не должен показываться при входе в логин-->
    <@l.login "/login" false/>
</@c.page>





<#--старый логин-->
<#--<#import "parts/common.ftlh" as c>-->
<#--<#import "parts/loginMacro.ftlh" as l>-->

<#--<@c.page>-->
<#--    Login page-->
<#--&lt;#&ndash;    здесь тот параметр прописали который был в макросе loginMacro.ftlh&ndash;&gt;-->
<#--    <@l.loginMacro "/login" />-->
<#--    <a href="/registration">Add new user</a>-->
<#--</@c.page>-->



