<#import "parts/add_object.ftlh" as a>
<@a.add "/related_projects/add" "/related_projects" "Добавление связанного проекта">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Тип проекта:</label>
        <div class="col-sm-9">
            <select name="projectType" class="browser-default custom-select">
                <#list projectTypes as projectType>
                    <option value="${projectType.id}">${projectType.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Название:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите полное название">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Название на русском языке:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="nameRus" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите полное название на русском">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Аббревиатура:</label>
        <div class="col-sm-9">
            <input type="text" name="abbreviation" class="form-control" placeholder="Введите аббревиатуру проекта/программы">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Аббревиатура на русском языке:</label>
        <div class="col-sm-9">
            <input type="text" name="abbreviationRus" class="form-control" placeholder="Введите аббревиатуру на русском языке">
        </div>
    </div>
</@a.add>