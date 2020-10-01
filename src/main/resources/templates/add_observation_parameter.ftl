<#import "parts/add_obj.ftl" as a>
<@a.add "/observation_parameters/add" "/observation_parameters" "Добавление параметра наблюдений">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label">Параметр наблюдений:</label>
        <div class="col-sm-9">
            <input required autofocus type="text"
                   name="name" class="form-control ${(messageError??)?string('is-invalid', '')}"
                   placeholder="Введите название параметра наблюдений">

            <#if messageError??>
                <div class="invalid-feedback">${messageError}</div>
            </#if>
        </div>
    </div>
    <div class="form-group row mt-3">
        <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
        <div class="col-sm-9">
            <select name="observationDiscipline" class="browser-default custom-select" id="getObservationDiscipline">
                <option value="0">-- Выберите дисциплину --</option>
                <#list observationDisciplines as observationDiscipline>
                    <option value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="form-group row mt-3">
        <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
        <div class="col-sm-9">
            <select name="observationType" class="browser-default custom-select" id="getObservationType" disabled>
                <option value="0">-- Выберите вид наблюдений --</option>
            </select>
        </div>
    </div>
    <#include "parts/scripts.ftl">
    <script type="text/javascript" src="/js/script.js"></script>
</@a.add>