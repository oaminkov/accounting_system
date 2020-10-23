<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Имя пользователя</th>
            <th>Роли пользователя</th>
        </tr>
        </thead>
        <tbody>
        <#list listUser as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <#if user.username != auth_username>
                    <td><a class="btn btn-warning btn-sm m-0" href="/user/${user.id}">Редактировать</a></td>
                    <#if user.active>
                        <td><a class="btn btn-danger btn-sm m-0" href="/user/activate/${user.id}">Заблокировать</a></td>
                    <#else>
                        <td><a class="btn btn-success btn-sm m-0" href="/user/activate/${user.id}">Разблокировать</a></td>
                    </#if>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-info btn-block my-2" href="/registration">Добавить пользователя</a>
</@c.page>