<#import "parts/common.ftlh" as c>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/messageEdit.ftlh" />
    </#if>

    <#include "parts/messageList.ftlh" />
</@c.page>





<#--<#import "parts/common.ftlh" as c>-->


<#--<@c.page>-->
<#--<#if isCurrentUser??>-->
<#--&lt;#&ndash; редактор сообщений только на собственной странице пользователя, на чужой странице не может редактировать&ndash;&gt;-->
<#--    <#include "parts/messageEdit.ftlh"/>-->
<#--</#if>-->

<#--    <#include "parts/messageList.ftlh"/>-->

<#--</@c.page>-->