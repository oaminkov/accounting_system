<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h1 class="mb-3" align="center">Просмотр информационного продукта</h1>
        <div class="form-group row mt-3">
            <label class="col-sm-3 col-form-label">Проект/программа:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.projectType.fullName}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Страна:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.country.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Язык:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.language.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.observationDiscipline.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.observationType.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.observationScope.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Географический объект:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.geographicalObject.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Организация:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.organization.fullName}">
            </div>
        </div>

        <hr>

        <div class="form-group row mt-3">
            <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="inventoryNumber" class="form-control" value="${informationProduct.inventoryNumber}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="fullnameCdrom" class="form-control" value="${informationProduct.fullnameCdrom}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="abbreviationCdrom" class="form-control" value="${informationProduct.abbreviationCdrom}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="dateObservationStart" class="form-control" value="${informationProduct.dateObservationStart}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="dateObservationEnd"  class="form-control" value="${informationProduct.dateObservationEnd}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Объём данных:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="volume" class="form-control" value="${informationProduct.volume}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата получения:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="receivedDate" class="form-control" value="${informationProduct.receivedDate}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Оператор:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationProduct.user.username}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата ввода в систему:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationProduct.dateOfEntering}">
            </div>
        </div>

        <#if informationProduct.editor??>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Редактор:</label>
                <div class="col-sm-9">
                    <input readonly type="text" class="form-control" value="${informationProduct.editor.username}">
                </div>
            </div>
        </#if>

        <#if informationProduct.dateOfEdit??>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата редактирования:</label>
                <div class="col-sm-9">
                    <input readonly type="text" class="form-control" value="${informationProduct.dateOfEdit}">
                </div>
            </div>
        </#if>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Краткое описание:</label>
            <div class="col-sm-9">
                <textarea readonly name="briefContent" class="form-control" rows="4">${informationProduct.briefContent}</textarea>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дубликат:</label>
            <div class="col-sm-9">
                <div class="custom-control custom-checkbox mb-3">
                    <input disabled type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" ${informationProduct.duplicate?string('checked', '')}>
                    <label class="custom-control-label" for="customCheck1"></label>
                </div>
            </div>
        </div>

        <a class="btn btn-secondary btn-block" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Файлы<i class="fas fa-chevron-down ml-2"></i>
        </a>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                <form action="/information_product/download/${informationProduct.id}">
                    <#if uploadedFiles?has_content>
                        <button type="submit" class="btn btn-success btn-block mb-2">Скачать архив</button>
                    <#else>
                        <p align="center">Файлы отсутствуют</p>
                    </#if>
                </form>
                <#list uploadedFiles as uploadedFile>
                    <a href="/file/${informationProduct.organization.id}/${informationProduct.inventoryNumber}/${uploadedFile.name}"
                       class="btn btn-block mb-2" target="_blank">${uploadedFile.name}</a>
                </#list>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col">
                <form action="/information_product/edit/${informationProduct.id}"><button type="submit" class="btn btn-warning m-0">Редактировать</button></form>
            </div>
            <div class="col text-right">
                <#if auth_isAdmin>
                    <form action="/information_product/delete/${informationProduct.id}"><button type="submit" class="btn btn-danger m-0">Удалить</button></form>
                </#if>
            </div>
        </div>

        <a href="/view_informationproduct" class="btn btn-blue-grey m-0 mt-3 mb-3">Назад</a>
    </div>
    <#include "parts/scripts.ftl">
<#include "parts/foot.ftl">