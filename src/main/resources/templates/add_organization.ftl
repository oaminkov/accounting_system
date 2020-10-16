<#import "parts/add_obj.ftl" as a>
<@a.add "/organizations/add" "/organizations" "Добавление организации">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Аббревиатура организации:</label>
        <div class="col-sm-9">
            <input required autofocus type="text" name="abbreviation"
                   class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите аббревиатуру организации">
            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Русское название организации:</label>
        <div class="col-sm-9">
            <input type="text" name="nameRus" class="form-control" placeholder="Введите русское название организации">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Английское название организации:</label>
        <div class="col-sm-9">
            <input type="text" name="nameEng" class="form-control" placeholder="Введите английское название организации">
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