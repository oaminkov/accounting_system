<#import "parts/common.ftl" as c>
<@c.page>
    <div align="center">
        <#if message?has_content>
            <div class="alert alert-danger">
                ${message}
            </div>
        </#if>
        <h1 class="mb-3">Добавить нового пользователя</h1>
        <form action="/registration" method="post" class="text-center">
            <input required type="text" name="username" id="defaultLoginFormEmail" class="form-control mb-3" placeholder="Логин">
            <input required type="password" name="password" id="defaultLoginFormPassword" class="form-control mb-3" placeholder="Пароль">
            <button class="btn btn-info btn-block my-3" type="submit" value="Зарегистрировать">Зарегистрировать</button>
        </form>
    </div>
</@c.page>