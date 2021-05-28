<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark special-color-dark sticky-top">
    <a class="navbar-brand" href="/main">Главная</a>
    <!-- Collapse button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicNav"
            aria-controls="basicNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collapsible content -->
    <div class="collapse navbar-collapse" id="basicNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/information_resources">Информационные ресурсы</a>
            </li>
            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Дополнительные сущности</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <span class="dropdown-menu-arrow"></span>
                    <a class="dropdown-item" href="/languages">Языки</a>
                    <a class="dropdown-item" href="/project_types">Типы проектов</a>
                    <a class="dropdown-item" href="/related_projects">Связанные проекты</a>
                    <a class="dropdown-item" href="/countries">Страны</a>
                    <a class="dropdown-item" href="/organizations">Организации</a>
                    <a class="dropdown-item" href="/observation_methods">Методы наблюдений</a>
                    <a class="dropdown-item" href="/observation_disciplines">Дисциплины наблюдений</a>
                    <a class="dropdown-item" href="/observation_types">Виды наблюдений</a>
                    <a class="dropdown-item" href="/observation_parameters">Параметры наблюдений</a>
                    <a class="dropdown-item" href="/observation_scopes">Сферы наблюдений</a>
                    <a class="dropdown-item" href="/observation_territories">Территории наблюдений</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/search">Поиск</a>
            </li>

            <#if auth_isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/users">Список пользователей</a>
                </li>
            </#if>
        </ul>

        <#if auth_user??>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown ml-auto">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-1" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><i class="fas fa-user"></i> ${auth_username} </a>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink-1">
                        <a class="dropdown-item" href="/users/profile">Профиль</a>
                        <a class="dropdown-item" href="/logout">Выйти</a>
                    </div>
                </li>
            </ul>
        <#else>
            <a class="nav-link btn-outline-primary" href="/login">Войти</a>
        </#if>
    </div>
    <!-- Collapsible content -->
</nav>
