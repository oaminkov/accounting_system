<#import "parts/add_object.ftlh" as a>
<@a.add "/observation_methods/add" "/observation_methods" "Добавление метода наблюдений">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название метода наблюдений">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>