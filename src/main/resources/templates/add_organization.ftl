<#import "parts/add_obj.ftl" as a>
<@a.add "/organizations/add" "/organizations" "Добавление организации">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Название организации:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите полное название организации">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Аббревиатура организации:</label>
        <div class="col-sm-9">
            <input type="text" name="abbreviation" class="form-control" placeholder="Введите аббревиатуру названия организации">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Страна:</label>
        <div class="col-sm-9">
            <select name="country" class="browser-default custom-select">
                <#list countries as country>
                    <option value="${country.id}">${country.name}</option>
                </#list>
            </select>
        </div>
    </div>
</@a.add>