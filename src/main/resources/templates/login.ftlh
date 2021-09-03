<#include "*/parts/head.ftlh">
    <main class="container"><br/>
        <#if RequestParameters.error??>
            <div class="alert alert-danger">
                Неверный логин или пароль.
            </div>
        </#if>
        <#if loggedUser?has_content>
            <#if message?has_content>
                <div>${message}</div>
            </#if>
        <#else>
            <#if message?has_content>
                <div>${message}</div>
            </#if>
            <form action="/login" method="post" class="text-center border border-light p-5 mb-3">
                <p class="h4 mb-4">Авторизация</p>
                <input type="text" name="username" id="defaultLoginFormUsername" class="form-control mb-4" placeholder="Логин" autofocus>
                <input type="password" name="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Пароль">
                <button class="btn btn-info btn-block my-4" type="submit" value="Login">Войти</button>
            </form>
        </#if>
        <a href="/main" class="btn btn-outline-secondary m-0 waves-effect">Перейти на главную страницу</a><br>
    </main>

    <#include "*/parts/footer.ftlh">
    <#include "*/parts/scripts.ftlh">
<#include "*/parts/foot.ftlh">