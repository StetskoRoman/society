<#import "parts/common.ftlh" as c>

<@c.page>
    List of users

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>


<#--<#import "parts/common.ftlh" as c>-->

<#--<@c.page>-->
<#--    List of users-->
<#--    <table>-->
<#--        <thead>-->
<#--        <tr>-->
<#--&lt;#&ndash;            <th>ID</th>&ndash;&gt;-->
<#--            <th>Name</th>-->
<#--            <th>Role</th>-->
<#--            <th></th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--    </table>-->

<#--    <#list users as user>-->
<#--        <tr>-->
<#--&lt;#&ndash;        <td>${user.id}</td>&ndash;&gt;-->
<#--        <td>${user.username}</td>-->
<#--        <td><#list user.roles as role>${role}<#sep>, </#list></td>-->
<#--        <td><a href="/user/${user.id}"> Edit </a></td><br>-->
<#--        </tr>-->
<#--    </#list>-->

<#--</@c.page>-->