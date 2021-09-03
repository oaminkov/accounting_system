<#import "*/macros/common.ftlh" as c>
<@c.page>
    <#include "*/parts/security.ftlh">

    <main class="container">
        <h2 align="center">Список пользователей</h2>

        <div class="d-flex justify-content-center mb-3">
            <a href="/registration" class="btn btn-primary">Добавить пользователя</a>
        </div>

        <table id="table_id" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Имя пользователя</th>
                    <th>Роли пользователя</th>
                    <th>Редактирование</th>
                    <th>Блокировка</th>
                </tr>
            </thead>
            <tbody>
            <#list listUser as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <#if user.username != auth_username>
                        <td><a class="btn btn-warning btn-sm m-0" href="/users/${user.id}">Редактировать</a></td>
                        <#if user.active>
                            <td><a class="btn btn-danger btn-sm m-0" href="/users/activate/${user.id}">Заблокировать</a></td>
                        <#else>
                            <td><a class="btn btn-success btn-sm m-0" href="/users/activate/${user.id}">Разблокировать</a></td>
                        </#if>
                    <#else>
                        <td></td>
                        <td></td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </main>

    <#include "*/parts/footer.ftlh">
    <#include "*/parts/scripts.ftlh">
    <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery('#table_id').DataTable( {
                paging: true,
                info: false,
                searching: true,
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.20/i18n/Russian.json"
                }
            });
        });
    </script>
</@c.page>