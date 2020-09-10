<#import "parts/common.ftl" as c>
<@c.page>
    <h5><strong>${username}</strong></h5>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input required type="password" name="password" class="form-control" placeholder="Введите пароль">
            </div>
        </div>
        <button class="btn btn-indigo btn-sm m-0" type="submit">Сохранить изменения</button>
    </form>
</@c.page>