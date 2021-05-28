<#import "parts/add_object.ftlh" as a>
<@a.add "/observation_disciplines/add" "/observation_disciplines" "Добавление дисциплины наблюдений">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название дисциплины наблюдения">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>