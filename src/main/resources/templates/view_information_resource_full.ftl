<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h1 class="mb-3" align="center">Просмотр информационного ресурса</h1>
        <div class="form-group row mt-3">
            <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="inventoryNumber" class="form-control" value="${informationResource.inventoryNumber}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="fullnameCdrom" class="form-control" value="${informationResource.fullnameCdrom}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="abbreviationCdrom" class="form-control" value="${informationResource.abbreviationCdrom}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="dateObservationStart" class="form-control" value="${informationResource.dateObservationStart}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="dateObservationEnd"  class="form-control" value="${informationResource.dateObservationEnd}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Объём данных:</label>
            <div class="col-sm-9">
                <input readonly type="text" name="volume" class="form-control" value="${informationResource.volume}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата получения:</label>
            <div class="col-sm-9">
                <input readonly type="date" name="receivedDate" class="form-control" value="${informationResource.receivedDate}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Краткое описание:</label>
            <div class="col-sm-9">
                <textarea readonly name="briefContent" class="form-control" rows="4">${informationResource.briefContent}</textarea>
            </div>
        </div>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Язык:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.language.name}">
            </div>
        </div>
        <div class="form-group row mt-3">
            <label class="col-sm-3 col-form-label">Связанный проект:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.relatedProject.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Страна:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.country.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.observationMethod.name}">
            </div>
        </div>
        <hr>

        <#list informationResource.observationDisciplines as observationDiscipline>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                <div class="col-sm-9">
                    <input type="text" readonly class="form-control" value="${observationDiscipline.name}">
                </div>
            </div>

            <#list observationDiscipline.observationTypes as observationType>
                <#if informationResource.observationTypes?seq_contains(observationType)>
                    <br>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                        <div class="col-sm-9">
                            <input type="text" readonly class="form-control" value="${observationType.name}">
                        </div>
                    </div>

                    <#list observationType.observationParameters as observationParameter>
                        <#if informationResource.observationParameters?seq_contains(observationParameter)>
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Параметр наблюдений:</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control" value="${observationParameter.name}">
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </#list>
            <hr>
        </#list>

        <#list informationResource.observationScopes as observationScope>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
                <div class="col-sm-9">
                    <input type="text" readonly class="form-control" value="${observationScope.name}">
                </div>
            </div>
        </#list>
        <hr>

        <#list informationResource.observationTerritories as observationTerritory>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Территория наблюдений:</label>
                <div class="col-sm-9">
                    <input type="text" readonly class="form-control" value="${observationTerritory.name}">
                </div>
            </div>
        </#list>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Главная организация:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.mainOrganization.name}">
            </div>
        </div>
        <#list informationResource.organizations as organization>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <input type="text" readonly class="form-control" value="${organization.name}">
                </div>
            </div>
        </#list>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дубликат:</label>
            <div class="col-sm-9">
                <div class="custom-control custom-checkbox mb-3">
                    <input disabled type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" ${informationResource.duplicate?string('checked', '')}>
                    <label class="custom-control-label" for="customCheck1"></label>
                </div>
            </div>
        </div>

        <#if informationResource.uploadedFiles?has_content>
            <a class="btn btn-secondary btn-block" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Файлы<i class="fas fa-chevron-down ml-2"></i>
            </a>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    <form action="/information_resources/download/${informationResource.id}">
                        <button type="submit" class="btn btn-success btn-block mb-2">Скачать архив</button>

                        <#list informationResource.uploadedFiles as uploadedFile>
                            <a href="/file/${informationResource.country.id}/${informationResource.mainOrganization.id}/${informationResource.inventoryNumber}/${uploadedFile.name}"
                               class="btn btn-block mb-2" target="_blank">${uploadedFile.name}</a>
                        </#list>
                    </form>
                </div>
            </div>
            <br><br>
        </#if>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Оператор:</label>
            <div class="col-sm-9">
                <input type="text" readonly class="form-control" value="${informationResource.operator.username}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата ввода в систему:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.dateOfEntering}">
            </div>
        </div>
        <hr>

        <#if informationResource.editor??>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Редактор:</label>
                <div class="col-sm-9">
                    <input readonly type="text" class="form-control" value="${informationResource.editor.username}">
                </div>
            </div>

            <#if informationResource.dateOfEdit??>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Дата редактирования:</label>
                    <div class="col-sm-9">
                        <input readonly type="text" class="form-control" value="${informationResource.dateOfEdit}">
                    </div>
                </div>
            </#if>
            <hr>
        </#if>

        <div class="row mt-3">
            <div class="col">
                <form action="/information_resources/edit/${informationResource.id}"><button type="submit" class="btn btn-warning m-0">Редактировать</button></form>
            </div>
            <div class="col text-right">
                <#if auth_isAdmin>
                    <form action="/information_resources/delete/${informationResource.id}"><button type="submit" class="btn btn-danger m-0">Удалить</button></form>
                </#if>
            </div>
        </div>
        <a href="/information_resources" class="btn btn-blue-grey m-0 mt-3 mb-3">Назад</a>
    </div>
    <#include "parts/scripts.ftl">
<#include "parts/foot.ftl">