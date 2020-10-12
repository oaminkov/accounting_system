<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h1 class="mb-3" align="center">Изменить мета-описание информационного ресурса</h1>
        <form action="/information_resources/update/${informationResource.id}" enctype="multipart/form-data" method="post">
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input required type="text" name="inventoryNumber" value="${informationResource.inventoryNumber}" class="form-control" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="fullnameCdrom" value="${informationResource.fullnameCdrom}" class="form-control" placeholder="Введите полное название CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="abbreviationCdrom" value="${informationResource.abbreviationCdrom}" class="form-control" placeholder="Введите аббревиатуру CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" name="dateObservationStart" value="${informationResource.dateObservationStart}" class="form-control" placeholder="Введите дату начала наблюдений дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" name="dateObservationEnd" value="${informationResource.dateObservationEnd}" class="form-control" placeholder="Введите дату окончания наблюдений в формате дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Объём данных:</label>
                <div class="col-sm-9">
                    <input type="text" name="volume" value="${informationResource.volume}" class="form-control" placeholder="Введите объём данных">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата получения:</label>
                <div class="col-sm-9">
                    <input type="date" name="receivedDate" value="${informationResource.receivedDate}" class="form-control" placeholder="Введите дату получения">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Краткое описание:</label>
                <div class="col-sm-9">
                    <textarea name="briefContent" class="form-control" rows="4" placeholder="Краткое описание информационного продукта">${informationResource.briefContent}</textarea>
                </div>
            </div>
            <hr>

            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Язык:</label>
                <div class="col-sm-9">
                    <select name="language" class="browser-default custom-select">
                        <#list languages as language>
                            <#if language == informationResource.language>
                                <option selected value="${language.id}">${language.name}</option>
                            <#else>
                                <option value="${language.id}">${language.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Связанный проект:</label>
                <div class="col-sm-9">
                    <select name="relatedProject" class="browser-default custom-select">
                        <#list relatedProjects as relatedProject>
                            <#if relatedProject == informationResource.relatedProject>
                                <option selected value="${relatedProject.id}">${relatedProject.name}</option>
                            <#else>
                                <option value="${relatedProject.id}">${relatedProject.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select name="country" class="browser-default custom-select">
                        <#list countries as country>
                            <#if country == informationResource.country>
                                <option selected value="${country.id}">${country.name}</option>
                            <#else>
                                <option value="${country.id}">${country.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationMethod" class="browser-default custom-select">
                        <#list observationMethods as observationMethod>
                            <#if observationMethod == informationResource.observationMethod>
                                <option selected value="${observationMethod.id}">${observationMethod.name}</option>
                            <#else>
                                <option value="${observationMethod.id}">${observationMethod.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <hr>

            <#--<div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationDiscipline" class="browser-default custom-select" id="getObservationDiscipline">
                        <#list observationDisciplines as observationDiscipline>
                            <#if observationDiscipline == informationResource.observationDiscipline>
                                <option selected value="${observationDiscipline.id}"
                                >${observationDiscipline.name}</option>
                            <#else>
                                <option value="${observationDiscipline.id}"
                                >${observationDiscipline.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationType" class="browser-default custom-select" id="getObservationType">
                        <#list observationTypes as observationType>
                            <#if observationType == informationResource.observationType>
                                <option selected value="${observationType.id}"
                                >${observationType.name}</option>
                            <#else>
                                <option value="${observationType.id}"
                                >${observationType.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationScope" class="browser-default custom-select">
                        <#list observationScopes as observationScope>
                            <#if observationScope == informationResource.observationScope>
                                <option selected value="${observationScope.id}"
                                >${observationScope.name}</option>
                            <#else>
                                <option value="${observationScope.id}"
                                >${observationScope.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Географический объект:</label>
                <div class="col-sm-9">
                    <select name="observationTerritory" class="browser-default custom-select">
                        <#list observationTerritories as observationTerritory>
                            <#if observationTerritory == informationResource.observationTerritory>
                                <option selected value="${observationTerritory.id}"
                                >${observationTerritory.name}</option>
                            <#else>
                                <option value="${observationTerritory.id}"
                                >${observationTerritory.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <select name="organization" class="browser-default custom-select">
                        <#list organizations as organization>
                            <#if organization == informationResource.organization>
                                <option selected value="${organization.id}"
                                >${organization.name}</option>
                            <#else>
                                <option value="${organization.id}"
                                >${organization.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>

            <hr>

            -->

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дубликат:</label>
                <div class="col-sm-9">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" value="1" ${informationResource.duplicate?string('checked', '')}>
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
                        <button type="submit" class="btn btn-danger btn-block mb-2" formaction="/information_products/delete_files/${informationResource.id}">
                            Удалить все прикреплённые файлы
                        </button>
                        <#list informationResource.uploadedFiles as uploadedFile>
                            <a href="/file/${informationResource.country.id}/${informationResource.inventoryNumber}/${uploadedFile.name}"
                               class="btn btn-block mb-2" target="_blank">${uploadedFile.name}</a>
                        </#list>
                    </div>
                </div>
                <br><br>
            </#if>

            <div class="input-group" align="center">
                <div class="custom-file overflow-hidden">
                    <input type="file" multiple name="uploadFiles" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
                    <label class="custom-file-label" for="inputGroupFile04">Выберите файлы</label>
                </div>
            </div>
            <button type="submit" class="btn btn-indigo btn-block m-0 mt-4">Сохранить</button><br/>
        </form>
        <a href="/information_resources" class="btn btn-blue-grey m-0 mb-3">Назад</a>
    </div>
    <#include "parts/scripts.ftl">
    <script>
        let disciplines =   '<#list observationDisciplines as observationDiscipline>' +
            '<option value="${observationDiscipline.id}">${observationDiscipline.name}</option>' +
            '</#list>';
        let scopes =    '<#list observationScopes as observationScope>' +
            '<option value="${observationScope.id}">${observationScope.name}</option>' +
            '</#list>';
        let territories =   '<#list observationTerritories as observationTerritory>' +
            '<option value="${observationTerritory.id}">${observationTerritory.name}</option>' +
            '</#list>';
        let organizations = '<#list organizations as organization>' +
            '<option value="${organization.id}">${organization.name}</option>' +
            '</#list>';
    </script>
    <script type="text/javascript" src="/js/add_infres.js"></script>
<#include "parts/foot.ftl">