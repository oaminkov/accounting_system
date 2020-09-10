<#import "parts/add_obj.ftl" as a>
<@a.add "/add_country" "/view_country" "Добавление страны">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Страна:</label>
        <div class="col-sm-10">
            <input required autofocus type="text"
                   name="nameCountry" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название страны">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>