<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>
    ${message!""}
<#--   Code from login. action deleted потому что форма отправляется туда же откуда пришла-->
    <form method="post">
<#--       тут было имя пользователя, его убрал и оно будет неизменяемо!-->

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <#-- email       Отображается поля только на поле регистрации-->

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!""}"/>
                </div>
            </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#--        ссылка показывается только это если не форма регистрации, т. е. на форме логина ссылка будет, на форме регистрации нет-->

        <button class="btn btn-primary" type="submit">Apply changes</button>
    </form>
</@c.page>