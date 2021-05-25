<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="/users" method="post">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Логин:</label>
            <div class="col-sm-9">
                <input required type="text" name="username" class="form-control" value="${user.username}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Пароль:</label>
            <div class="col-sm-9">
                <input required type="password" name="password" class="form-control" placeholder="Введите пароль">
            </div>
        </div>
        <#list roles as role>
            <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck${role}" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="custom-control-label pl-2" for="customCheck${role}">${role}</label>
            </div>
        </#list>
        <hr>

        <button type="submit" class="btn btn-success btn-block m-0">Сохранить изменения</button>
        <input type="hidden" value="${user.id}" name="userId">
    </form>
    <a href="/users" class="btn btn-outline-secondary m-0 my-4 waves-effect">Назад</a>
</@c.page>