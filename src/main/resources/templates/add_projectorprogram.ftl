<#import "parts/add_obj.ftl" as a>
<@a.add "/add_projectorprogram" "/view_projectorprogram" "Добавление проекта/программы">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Проект/программа:</label>
        <div class="col-sm-9">
            <select name="choiceProjectOrProgram" class="browser-default custom-select">
                <option value="Проект">Проект</option>
                <option value="Программа">Программа</option>
            </select>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Название:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="fullnameProjectOrProgram" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите полное название">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Аббревиатура:</label>
        <div class="col-sm-9">
            <input type="text" name="abbreviationProjectOrProgram" class="form-control" placeholder="Введите аббревиатуру проекта/программы">
        </div>
    </div>
</@a.add>