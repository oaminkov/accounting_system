<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-dark primary-color">
    <a class="navbar-brand" href="/main">Главная</a>
    <!-- Collapse button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
            aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collapsible content -->
    <div class="collapse navbar-collapse" id="basicExampleNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/information_resources">Информационные ресурсы
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Дополнительные сущности</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/languages">Языки</a>
                    <a class="dropdown-item" href="/related_projects">Связанные проекты</a>
                    <a class="dropdown-item" href="/countries">Страны</a>
                    <a class="dropdown-item" href="/observation_methods">Методы наблюдений</a>
                    <a class="dropdown-item" href="/observation_disciplines">Дисциплины наблюдений</a>
                    <a class="dropdown-item" href="/observation_types">Виды наблюдений</a>
                    <a class="dropdown-item" href="/observation_parameters">Параметры наблюдений</a>
                    <a class="dropdown-item" href="/observation_scopes">Сферы наблюдений</a>
                    <a class="dropdown-item" href="/geographical_objects">Территории наблюдений</a>
                    <a class="dropdown-item" href="/organizations">Организации</a>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/search">Поиск
                    <span class="sr-only">(current)</span>
                </a>
            </li>

            <!--<#if auth_user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Профиль
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </#if>-->

            <#if auth_isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3" style="color:yellow;">${auth_username}</div>

        <#if auth_user??>
            <form action="/logout" method="get">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="submit" value="Logout" class="btn btn-primary">Выйти</button>
                </div>
            </form>
        <#else>
            <form action="/login" method="get">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="submit" value="Login" class="btn btn-primary">Войти</button>
                </div>
            </form>
        </#if>
    </div>
    <!-- Collapsible content -->
</nav><br>
