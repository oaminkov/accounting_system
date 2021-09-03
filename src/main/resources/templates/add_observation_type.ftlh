<#import "macros/add_object.ftlh" as a>
<@a.add "/observation_types/add" "/observation_types" "Добавление вида наблюдений">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
        <div class="col-sm-9">
            <select name="observationDiscipline" class="select2 form-control">
                <#list observationDisciplines as observationDiscipline>
                    <option value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название вида наблюдений">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>