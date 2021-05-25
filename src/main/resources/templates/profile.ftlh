<#import "parts/common.ftlh" as c>
<@c.page>
    <h2 class="mb-3" align="center">Профиль</h2>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Пароль:</label>
            <div class="col-sm-9">
                <input required type="password" name="password" class="form-control" placeholder="Введите пароль">
            </div>
        </div>
        <hr>

        <button type="submit" class="btn btn-success btn-block m-0">Сохранить изменения</button>
    </form>
</@c.page>