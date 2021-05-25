<#import "parts/common.ftlh" as c>
<@c.page>
    <div align="center">
        <#if message?has_content>
            <div id="errorMessage" class="alert alert-danger">
                ${message}
            </div>
        </#if>
        <h2 class="mb-3">Добавить нового пользователя</h2>
        <form action="/registration" method="post" class="text-center">
            <input required type="text" name="username" id="defaultLoginFormEmail" class="form-control mb-3" placeholder="Логин">
            <input required type="password" name="password" id="defaultLoginFormPassword" class="form-control mb-3" placeholder="Пароль">
            <hr>

            <button class="btn btn-success btn-block" type="submit" value="Зарегистрировать">Зарегистрировать</button>
        </form>
    </div>
    <a href="/users" class="btn btn-outline-secondary m-0 my-4 waves-effect">Назад</a>
</@c.page>