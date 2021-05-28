<#include "parts/head.ftlh">
    <#include "parts/navbar.ftlh">
    <div class="container">
        <h2 class="mb-3" align="center">Изменение мета-описания информационного ресурса</h2>

        <form action="/information_resources/edit/${informationResource.id}" method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Тип ресурса:</label>
                <div class="col-sm-9">
                    <select name="resourceType" class="select2 form-control">
                        <#list resourceTypes as resourceType>
                            <#if resourceType == informationResource.resourceType>
                                <option selected value="${resourceType.id}">${resourceType.name}</option>
                            <#else>
                                <option value="${resourceType.id}">${resourceType.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <hr>

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
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Язык представления:</label>
                <div class="col-sm-9">
                    <select name="language" class="select2 form-control">
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
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select name="country" class="select2 form-control">
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
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Главная организация:</label>
                <div class="col-sm-9 input-group">
                    <select name="mainOrganization" class="select2 form-control getOrganization">
                        <#list organizations as organization>
                            <#if organization == informationResource.mainOrganization>
                                <option selected value="${organization.id}">${organization.name}</option>
                            <#else>
                                <option value="${organization.id}">${organization.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <#list informationResource.organizations as infresOrganization>
                <div class="additionalBlock">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Организация:</label>
                        <div class="col-sm-9 input-group">
                            <select name="organization" class="select2 form-control getOrganization">
                                <#list organizations as organization>
                                    <#if organization == infresOrganization>
                                        <option selected value="${organization.id}">${organization.name}</option>
                                    <#else>
                                        <option value="${organization.id}">${organization.name}</option>
                                    </#if>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addOrganization">Добавить организацию</button>
            </div>
            <hr>

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
                <label class="col-sm-3 col-form-label">Дата получения CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="date" name="receivedDate" value="${informationResource.receivedDate}" class="form-control" placeholder="Введите дату получения">
                </div>
            </div>
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Связанный проект:</label>
                <div class="col-sm-9">
                    <select name="relatedProject" class="select2 form-control">
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
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationMethod" class="select2 form-control">
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

            <#list informationResource.observationDisciplines as infresObservationDiscipline>
                <div class="discDiv additionalBlock">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                        <div class="col-sm-9 input-group">
                            <select name="observationDiscipline" class="select2 form-control getObservationDiscipline">
                                <#list observationDisciplines as observationDiscipline>
                                    <#if observationDiscipline == infresObservationDiscipline>
                                        <option selected value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                                    <#else>
                                        <option value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                                    </#if>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                            </div>
                        </div>
                    </div>
                    <#list infresObservationDiscipline.observationTypes as infresObservationType>
                        <#if informationResource.observationTypes?seq_contains(infresObservationType)>
                            <div class="typeDiv additionalBlock">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                                    <div class="col-sm-9 input-group">
                                        <select name="observationType" class="select2 form-control getObservationType">
                                            <#list infresObservationDiscipline.observationTypes as observationType>
                                                <#if observationType == infresObservationType>
                                                    <option selected value="${observationType.id}">${observationType.name}</option>
                                                <#else>
                                                    <option value="${observationType.id}">${observationType.name}</option>
                                                </#if>
                                            </#list>
                                        </select>
                                        <div class="input-group-append">
                                            <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                                        </div>
                                    </div>
                                </div>

                                <#list infresObservationType.observationParameters as infresObservationParameter>
                                    <#if informationResource.observationParameters?seq_contains(infresObservationParameter)>
                                        <div class="paramDiv additionalBlock">
                                            <div class="form-group row mb-0">
                                                <label class="col-sm-3 col-form-label">Параметр наблюдений:</label>
                                                <div class="col-sm-9 input-group">
                                                    <select name="observationParameter" class="select2 form-control getObservationParameter">
                                                        <#list infresObservationType.observationParameters as observationParameter>
                                                            <#if observationParameter == infresObservationParameter>
                                                                <option selected value="${observationParameter.id}">${observationParameter.name}</option>
                                                            <#else>
                                                                <option value="${observationParameter.id}">${observationParameter.name}</option>
                                                            </#if>
                                                        </#list>
                                                    </select>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                                <div class="form-group row m-0">
                                    <button type="button" class="btn btn-primary btn-block addParam">Добавить параметр</button>
                                </div>
                                <hr>
                            </div>
                        </#if>
                    </#list>
                    <div class="form-group row m-0">
                        <button type="button" class="btn btn-primary btn-block addType">Добавить вид</button>
                    </div>
                    <hr>
                </div>
            </#list>
            <div class="form-group row m-0 mb-3">
                <button type="button" class="btn btn-primary btn-block addDisc">Добавить дисциплину</button>
            </div>
            <hr>

            <#list informationResource.observationScopes as infresObservationScope>
                <div class="additionalBlock">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
                        <div class="col-sm-9 input-group">
                            <select name="observationScope" class="select2 form-control getObservationScope">
                                <#list observationScopes as observationScope>
                                    <#if observationScope == infresObservationScope>
                                        <option selected value="${observationScope.id}">${observationScope.name}</option>
                                    <#else>
                                        <option value="${observationScope.id}">${observationScope.name}</option>
                                    </#if>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <div class="form-group row m-0 mb-3">
                <button type="button" class="btn btn-primary btn-block addScope">Добавить сферу наблюдений</button>
            </div>
            <hr>

            <#list informationResource.observationTerritories as infresObservationTerritory>
                <div class="additionalBlock">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Территория наблюдений:</label>
                        <div class="col-sm-9 input-group">
                            <select name="observationTerritory" class="select2 form-control getObservationTerritory">
                                <#list observationTerritories as observationTerritory>
                                    <#if observationTerritory == infresObservationTerritory>
                                        <option selected value="${observationTerritory.id}">${observationTerritory.name}</option>
                                    <#else>
                                        <option value="${observationTerritory.id}">${observationTerritory.name}</option>
                                    </#if>
                                </#list>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delBtn" type="button">x</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addTerritory">Добавить территорию наблюдений</button>
            </div>
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Краткое описание содержания:</label>
                <div class="col-sm-9">
                    <textarea name="briefContent" class="form-control" rows="2" placeholder="Краткое описание информационного ресурса">${informationResource.briefContent}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input required type="text" name="inventoryNumber" value="${informationResource.inventoryNumber}" class="form-control" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дубликат:</label>
                <div class="col-sm-9">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" ${informationResource.duplicate?string('checked', '')}>
                        <label class="custom-control-label" for="customCheck1"></label>
                    </div>
                </div>
            </div>

            <#if informationResource.uploadedFiles?has_content>
                <input type="hidden" name="delAttachedFiles" id="delAttachedFiles" value="0">
                <div class="divAttachedFiles">
                    <a class="btn btn-block btn-info" data-toggle="collapse" href="#collapseFiles" role="button" aria-expanded="false" aria-controls="collapseFiles">
                        <i class="fas fa-chevron-down mr-2"></i>Файлы<i class="fas fa-chevron-down ml-2"></i>
                    </a>
                    <div class="collapse" id="collapseFiles">
                        <div class="card card-body">
                            <button type="button" class="btn btn-danger btn-block mb-2 delAllAttachedFiles">
                                Удалить все прикреплённые файлы
                            </button>
                            <#list informationResource.uploadedFiles as uploadedFile>
                                <a href="/file/${informationResource.country.id}/${informationResource.mainOrganization.id}/${informationResource.inventoryNumber}/${uploadedFile.name}"
                                   class="btn btn-block mb-2" target="_blank">${uploadedFile.name}</a>
                            </#list>
                        </div>
                    </div>
                </div>
            </#if>
            <hr>

            <div class="input-group" align="center">
                <div class="custom-file overflow-hidden">
                    <input type="file" multiple name="uploadFiles" class="custom-file-input" id="customFile1">
                    <label class="custom-file-label" for="customFile1">Прикрепить файлы</label>
                </div>
            </div>
            <hr>

            <button type="submit" class="btn btn-success btn-block m-0">Сохранить</button><br/>
        </form>
        <a href="${backAddress}" class="btn btn-outline-secondary m-0 mb-3 waves-effect">Назад</a>
    </div>
    <#include "parts/footer.ftlh">

    <#include "parts/scripts.ftlh">
    <script>
        let organizations = '<#list organizations as organization>' +
                                '<option value="${organization.id}">${organization.name}</option>' +
                            '</#list>';
        let disciplines =   '<#list observationDisciplines as observationDiscipline>' +
                                '<option value="${observationDiscipline.id}">${observationDiscipline.name}</option>' +
                            '</#list>';
        let scopes =        '<#list observationScopes as observationScope>' +
                                '<option value="${observationScope.id}">${observationScope.name}</option>' +
                            '</#list>';
        let territories =   '<#list observationTerritories as observationTerritory>' +
                                '<option value="${observationTerritory.id}">${observationTerritory.name}</option>' +
                            '</#list>';
        let infresId = '${informationResource.id}';
    </script>
    <script type="text/javascript" src="/js/add_infres.js"></script>

<#include "parts/foot.ftlh">