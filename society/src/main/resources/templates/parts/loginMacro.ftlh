<#-- path и  isRegisterForm это два параметра, если не регистрации форма то будет только имя и пароль, а если регистрации то далее -->
<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="User name" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Retype password" />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com" />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="col-sm-6">
<#--                вставил свой сайт кей-->
                <div class="g-recaptcha" data-sitekey="6LeDw8YoAAAAAE13FhefUS3GpC-FMpArf7CttNyJ"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
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








<#--логин До валидации-->
<#--&lt;#&ndash;Логин под бутстрап&ndash;&gt;-->
<#--<#macro login path isRegisterForm>-->
<#--    <form action="${path}" method="post">-->
<#--        <div class="form-group row">-->
<#--&lt;#&ndash;            col-sm-2 - ширина 2 колонки&ndash;&gt;-->
<#--            <label class="col-sm-2 col-form-label">User Name :</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="text" name="username" class="form-control" placeholder="User name" />-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label">Password:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="password" name="password" class="form-control" placeholder="Password" />-->
<#--            </div>-->
<#--        </div>-->
<#--&lt;#&ndash; email       Отображается поля только на поле регистрации&ndash;&gt;-->
<#--        <#if isRegisterForm>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label">email:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="email" name="email" class="form-control" placeholder="some@some.com" />-->
<#--            </div>-->
<#--        </div>-->
<#--        </#if>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--&lt;#&ndash;        ссылка показывается только это если не форма регистрации, т. е. на форме логина ссылка будет, на форме регистрации нет&ndash;&gt;-->
<#--        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>-->
<#--        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>-->
<#--    </form>-->
<#--</#macro>-->

<#--<#macro logout>-->
<#--    <form action="/logout" method="post">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <button class="btn btn-primary" type="submit">Sign Out</button>-->
<#--    </form>-->
<#--</#macro>-->





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