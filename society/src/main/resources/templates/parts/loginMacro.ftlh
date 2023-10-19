<#--Логин под бутстрап-->
<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
<#--            col-sm-2 - ширина 2 колонки-->
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
<#-- email       Отображается поля только на поле регистрации-->
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="some@some.com" />
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
<#--        ссылка показывается только это если не форма регистрации, т. е. на форме логина ссылка будет, на форме регистрации нет-->
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>





<#--старая версия до бустрапа-->
<#--<#macro loginMacro path>-->
<#--&lt;#&ndash;login - название, path - переменная, до этого был action="/login", конкретный адрес куда перейти надо при подтверждении логина &ndash;&gt;-->
<#--&lt;#&ndash;    скорей всего здесь косяк с изменением роли пользователся&ndash;&gt;-->
<#--    <form action="${path}" method="post">-->
<#--        <div><label> User Name : <input type="text" name="username"/> </label></div>-->
<#--        <div><label> Password: <input type="password" name="password"/> </label></div>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <div><input type="submit" value="Sign In"/></div>-->
<#--    </form>-->
<#--</#macro>-->
<#--&lt;#&ndash;на всех будет логаут страницах&ndash;&gt;-->
<#--<#macro logout>-->
<#--    <form action="/logout" method="post">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <input type="submit" value="Sign Out"/>-->
<#--    </form>-->
<#--</#macro>-->