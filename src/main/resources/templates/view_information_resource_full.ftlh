<#include "parts/head.ftlh">
    <#include "parts/navbar.ftlh">
    <div class="container">
        <h2 class="mb-3" align="center">Просмотр мета-описания информационного ресурса</h2>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Тип ресурса:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.resourceType.name}">
            </div>
        </div>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.fullnameCdrom}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.abbreviationCdrom}">
            </div>
        </div>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Язык представления:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.language.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Страна:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.country.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Главная организация:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.mainOrganization.name}">
            </div>
        </div>
        <#list informationResource.organizations as organization>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <input readonly type="text" class="form-control" value="${organization.name}">
                </div>
            </div>
        </#list>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" class="form-control" value="${informationResource.dateObservationStart}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="date" class="form-control" value="${informationResource.dateObservationEnd}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Объём данных:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.volume}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата получения CD-ROM:</label>
            <div class="col-sm-9">
                <input readonly type="date" class="form-control" value="${informationResource.receivedDate}">
            </div>
        </div>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Связанный проект:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.relatedProject.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.observationMethod.name}">
            </div>
        </div>
        <hr>

        <#if informationResource.observationDisciplines?has_content>
            <#list informationResource.observationDisciplines as observationDiscipline>
                <div class="discDiv">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control" value="${observationDiscipline.name}">
                        </div>
                    </div>
                    <#list observationDiscipline.observationTypes as observationType>
                        <#if informationResource.observationTypes?seq_contains(observationType)>
                            <div class="typeDiv">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                                    <div class="col-sm-9">
                                        <input readonly type="text" class="form-control" value="${observationType.name}">
                                    </div>
                                </div>
                                <#list observationType.observationParameters as observationParameter>
                                    <#if informationResource.observationParameters?seq_contains(observationParameter)>
                                        <div class="paramDiv">
                                            <div class="form-group row mb-0">
                                                <label class="col-sm-3 col-form-label">Параметр наблюдений:</label>
                                                <div class="col-sm-9">
                                                    <input readonly type="text" class="form-control" value="${observationParameter.name}">
                                                </div>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                                <hr>
                            </div>
                        </#if>
                    </#list>
                    <hr>
                </div>
            </#list>
            <hr>
        </#if>

        <#if informationResource.observationScopes?has_content>
            <#list informationResource.observationScopes as observationScope>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
                    <div class="col-sm-9">
                        <input readonly type="text" class="form-control" value="${observationScope.name}">
                    </div>
                </div>
            </#list>
            <hr>
        </#if>

        <#if informationResource.observationTerritories?has_content>
            <#list informationResource.observationTerritories as observationTerritory>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Территория наблюдений:</label>
                    <div class="col-sm-9">
                        <input readonly type="text" class="form-control" value="${observationTerritory.name}">
                    </div>
                </div>
            </#list>
            <hr>
        </#if>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Краткое описание:</label>
            <div class="col-sm-9">
                <textarea readonly class="form-control" rows="2">${informationResource.briefContent}</textarea>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.inventoryNumber}">
            </div>
        </div>
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
            <a class="btn btn-info btn-block" data-toggle="collapse" href="#collapseFiles" role="button" aria-expanded="false" aria-controls="collapseFiles">
                <i class="fas fa-chevron-down mr-2"></i>Файлы<i class="fas fa-chevron-down ml-2"></i>
            </a>
            <div class="collapse" id="collapseFiles">
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
        </#if>
        <hr>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Дата ввода в систему:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.dateOfEntering}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Оператор:</label>
            <div class="col-sm-9">
                <input readonly type="text" class="form-control" value="${informationResource.operator.username}">
            </div>
        </div>
        <hr>

        <#if informationResource.dateOfEdit??>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата редактирования:</label>
                <div class="col-sm-9">
                    <input readonly type="text" class="form-control" value="${informationResource.dateOfEdit}">
                </div>
            </div>

            <#if informationResource.editor??>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Редактор:</label>
                    <div class="col-sm-9">
                        <input readonly type="text" class="form-control" value="${informationResource.editor.username}">
                    </div>
                </div>
            </#if>
            <hr>
        </#if>

        <div class="row">
            <div class="col">
                <form action="/information_resources/edit/${informationResource.id}"><button type="submit" class="btn btn-warning m-0">Редактировать</button></form>
            </div>
            <div class="col text-right">
                <#if auth_isAdmin>
                    <form action="/information_resources/delete/${informationResource.id}"><button type="submit" class="btn btn-danger m-0">Удалить</button></form>
                </#if>
            </div>
        </div>
        <a href="/information_resources" class="btn btn-outline-secondary m-0 my-4 waves-effect">Назад</a>
    </div>
    <#include "parts/scripts.ftlh">
<#include "parts/foot.ftlh">