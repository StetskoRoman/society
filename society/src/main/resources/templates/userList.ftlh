<#import "parts/common.ftlh" as c>

<@c.page>
    List of users
    <table>
        <thead>
        <tr>
<#--            <th>ID</th>-->
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
    </table>

    <#list users as user>
        <tr>
<#--        <td>${user.id}</td>-->
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}"> Edit </a></td><br>
        </tr>
    </#list>

</@c.page>