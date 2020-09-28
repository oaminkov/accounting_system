<#import "parts/add_obj.ftl" as a>
<@a.add "/geographical_objects/add" "/geographical_objects" "Добавление территории наблюдений">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Территория наблюдений:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название территории наблюдений">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
</@a.add>