<#include "parts/head.ftl">
    <div class="container"><br/>
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
                <!-- Default form login -->
                <form action="/login" method="post" class="text-center border border-light p-5">
                    <p class="h4 mb-4">Авторизация</p>
                    <!-- Login -->
                    <input type="text" name="username" id="defaultLoginFormUsername" class="form-control mb-4" placeholder="Логин" autofocus>
                    <!-- Password -->
                    <input type="password" name="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Пароль">
                    <!-- Sign in button -->
                    <button class="btn btn-info btn-block my-4" type="submit" value="Login">Войти</button>
                </form><br>
                <!-- Default form login -->
        </#if>
        <a href="/main">Перейти на главную страницу</a><br>
    </div>
<#include "parts/foot.ftl">