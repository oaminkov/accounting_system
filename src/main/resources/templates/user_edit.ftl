<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/user" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Логин:</label>
            <div class="col-sm-6">
                <input required type="text" name="username" class="form-control" value="${user.username}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input required type="password" name="password" class="form-control" placeholder="Введите пароль">
            </div>
        </div>
        <#list roles as role>
            <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck${role}" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="custom-control-label" for="customCheck${role}">${role}</label>
            </div>
        </#list>
        <button type="submit" class="btn btn-indigo btn-sm m-0" value="save-projectorprogram">Сохранить изменения</button>
        <input type="hidden" value="${user.id}" name="userId">
    </form>
</@c.page>