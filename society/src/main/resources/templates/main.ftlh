<#--два варианта типа было и стало-->
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse <#if message??>show</#if>"  id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
<#--                    не работает это, вставлял именно то что надо-->
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
<#--                    Из класса ControllerUtils -->
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>













<#--<#import "parts/common.ftlh" as c>-->

<#--<@c.page>-->
<#--    <div class="form-row">-->
<#--        <div class="form-group col-md-6">-->
<#--            <form method="get" action="/main" class="form-inline">-->
<#--                &lt;#&ndash;        value="${filter!}"> означает что переменной фильтр может и не быть!&ndash;&gt;-->
<#--                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">-->
<#--                &lt;#&ndash;        <input type="hidden" name="_csrf" value="${_csrf.token}"/>&ndash;&gt;-->
<#--                <button type="submit" class="btn btn-primary ml-2">Search</button>-->
<#--            </form>-->
<#--        </div>-->
<#--    </div>-->
<#--&lt;#&ndash;в Collapse ссылка https://getbootstrap.com/docs/4.1/components/collapse/ &ndash;&gt;-->
<#--    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"-->
<#--       aria-controls="collapseExample">-->
<#--        Add new message-->
<#--    </a>-->
<#--&lt;#&ndash; в див класс тоже прописывать&ndash;&gt;-->
<#--    <div class="collapse" id="collapseExample">-->
<#--        &lt;#&ndash;   enctype="multipart/form-data" позволяет файлы передавать     &ndash;&gt;-->
<#--        <form method="post" enctype="multipart/form-data">-->
<#--            <input type="text" name="text" placeholder="Write message"/><br>-->
<#--            <input type="text" name="tag" placeholder="Tag@"><br>-->
<#--            <input type="file" name="file">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button type="submit">Add message</button>-->
<#--        </form>-->
<#--    </div>-->
<#--    <div>List of messages</div>-->

<#--&lt;#&ndash;    в цикле обход сообщений&ndash;&gt;-->
<#--    <#list messages as message>-->
<#--        <div>-->
<#--            <b>${message.id}</b>-->
<#--            <span>${message.text}</span>-->
<#--            <i>${message.tag}</i>-->
<#--            <strong>${message.authorName}</strong>-->
<#--            <div>-->
<#--                &lt;#&ndash;        <#if message.filename??> здесь ?? - это приведение к типу boolean        &ndash;&gt;-->
<#--                <#if message.filename??>-->
<#--                    <img src="/img/${message.filename}">-->
<#--                </#if>-->
<#--            </div>-->
<#--        </div>-->
<#--    <#else> No messages-->
<#--    </#list>-->

<#--</@c.page>-->