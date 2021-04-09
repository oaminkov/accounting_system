<#import "parts/add_obj.ftl" as a>
<@a.add "/sources/add" "/sources" "Добавление источника">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Источник:</label>
        <div class="col-sm-10">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название источника">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>