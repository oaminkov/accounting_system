<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h1 class="mb-3" align="center">Изменить мета-описание информационного продукта</h1>
        <form action="/information_product/update/${informationProduct.id}" enctype="multipart/form-data" method="post">
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Проект/программа:</label>
                <div class="col-sm-9">
                    <select name="projectType" class="browser-default custom-select">
                        <#list listProjectOrProgram as projectType>
                            <#if projectType == informationProduct.projectType>
                                <option selected value="${projectType.id}"
                                >${projectType.fullName}</option>
                            <#else>
                                <option value="${projectType.id}"
                                >${projectType.fullName}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select name="country" class="browser-default custom-select">
                        <#list listCountry as country>
                            <#if country == informationProduct.country>
                                <option selected value="${country.id}"
                                >${country.name}</option>
                            <#else>
                                <option value="${country.id}"
                                >${country.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Язык:</label>
                <div class="col-sm-9">
                    <select name="language" class="browser-default custom-select">
                        <#list listLanguage as language>
                            <#if language == informationProduct.language>
                                <option selected value="${language.id}"
                                >${language.name}</option>
                            <#else>
                                <option value="${language.id}"
                                >${language.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationDiscipline" class="browser-default custom-select" id="getObservationDiscipline">
                        <#list listObservationDiscipline as observationDiscipline>
                            <#if observationDiscipline == informationProduct.observationDiscipline>
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
                        <#list listObservationType as observationType>
                            <#if observationType == informationProduct.observationType>
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
                        <#list listObservationScope as observationScope>
                        <#if observationScope == informationProduct.observationScope>
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
                    <select name="geographicalObject" class="browser-default custom-select">
                        <#list listGeographicalObject as geographicalObject>
                        <#if geographicalObject == informationProduct.geographicalObject>
                        <option selected value="${geographicalObject.id}"
                        >${geographicalObject.name}</option>
                        <#else>
                        <option value="${geographicalObject.id}"
                        >${geographicalObject.name}</option>
                        </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <select name="organization" class="browser-default custom-select">
                        <#list listOrganization as organization>
                        <#if organization == informationProduct.organization>
                        <option selected value="${organization.id}"
                        >${organization.fullName}</option>
                        <#else>
                        <option value="${organization.id}"
                        >${organization.fullName}</option>
                        </#if>
                        </#list>
                    </select>
                </div>
            </div>

            <hr>

            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input required type="text" name="inventoryNumber" value="${informationProduct.inventoryNumber}" class="form-control" id="formGroupExampleInput" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="fullnameCdrom" value="${informationProduct.fullnameCdrom}" class="form-control" id="formGroupExampleInput2" placeholder="Введите полное название CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="abbreviationCdrom" value="${informationProduct.abbreviationCdrom}" class="form-control" id="formGroupExampleInput3" placeholder="Введите аббревиатуру названия CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" value="${informationProduct.dateObservationStart}" name="dateObservationStart" class="form-control" placeholder="Введите дату начала наблюдений дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" value="${informationProduct.dateObservationEnd}" name="dateObservationEnd"  class="form-control" placeholder="Введите дату окончания наблюдений в формате дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Объём данных:</label>
                <div class="col-sm-9">
                    <input type="text" value="${informationProduct.volume}" name="volume" class="form-control" placeholder="Введите объём данных">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата получения:</label>
                <div class="col-sm-9">
                    <input type="date" value="${informationProduct.receivedDate}" name="receivedDate" class="form-control" placeholder="Введите дату получения">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Краткое описание:</label>
                <div class="col-sm-9">
                    <textarea name="briefContent" class="form-control" rows="4" placeholder="Краткое описание информационного продукта">${informationProduct.briefContent}</textarea>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дубликат:</label>
                <div class="col-sm-9">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" value="Да" ${informationProduct.duplicate?string('checked', '')}>
                        <label class="custom-control-label" for="customCheck1"></label>
                    </div>
                </div>
            </div>

            <a class="btn btn-secondary btn-block" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Файлы<i class="fas fa-chevron-down ml-2"></i>
            </a>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    <#if uploadedFiles?has_content>
                        <button type="submit" class="btn btn-danger btn-block mb-2" formaction="/information_product/delete_files/${informationProduct.id}">
                            Удалить все прикреплённые файлы
                        </button>
                    <#else>
                        <p align="center">Файлы отсутствуют</p>
                    </#if>
                    <#list uploadedFiles as uploadedFile>
                        <a href="/file/${informationProduct.organization.id}/${informationProduct.inventoryNumber}/${uploadedFile.name}"
                           class="btn btn-block mb-2" target="_blank">${uploadedFile.name}</a>
                    </#list>
                </div>
            </div>

            <div class="input-group mt-3" align="center">
                <div class="custom-file">
                    <input type="file" multiple name="uploadFiles" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
                    <label class="custom-file-label" for="inputGroupFile04">Выберите файлы</label>
                </div>
            </div>
            <button type="submit" class="btn btn-indigo btn-block mt-3" value="save-informationproduct">Сохранить</button><br/>
            <a href="/view_informationproduct" class="btn btn-blue-grey m-0 mb-3">Назад</a>
        </form>
    </div>
    <#include "parts/scripts.ftl">
    <script type="text/javascript" src="/js/script.js"></script>
<#include "parts/foot.ftl">