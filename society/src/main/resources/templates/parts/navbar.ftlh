<#import "loginMacro.ftlh" as l>
<#include "security.ftlh">
<#--https://getbootstrap.com/docs/4.1/components/navbar/ панель навигации -->
<#-- в зависимости от ширины экрана мы будем видеть либо пару элементов панели либо все элементы сразу, navbar-expand-lg - значит меньше чем large экраны будут видет свернутую версию-->

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<#--    название проекта, ведет на главую страницу (/)-->
    <a class="navbar-brand" href="/">society</a>
<#--    кнопка -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<#--Описание самого меню-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Messages </a>
            </li>
<#--            только у аdмина будет доступ к странице ролей-->
            <#if isAdmin>
            <li class="nav-item active">
                <a class="nav-link" href="/user">User list </a>
            </li>
            </#if>
        </ul>
<#--        имя пользователя отобразит у кнопки выхода-->
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </div>
</nav>