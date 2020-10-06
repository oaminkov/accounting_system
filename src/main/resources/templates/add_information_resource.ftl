<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h1 class="mb-3" align="center">Создание нового мета-описания информационного ресурса</h1>
        <form action="/information_resources/add" enctype="multipart/form-data" method="post">
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Язык:</label>
                <div class="col-sm-9">
                    <select name="language" class="browser-default custom-select">
                        <option value="">-- Выберите язык --</option>
                        <#list languages as language>
                            <option value="${language.id}"
                            >${language.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Связанный проект:</label>
                <div class="col-sm-9">
                    <select name="relatedProject" class="browser-default custom-select">
                        <option value="">-- Выберите связанный проект --</option>
                        <#list relatedProjects as relatedProject>
                            <option value="${relatedProject.id}"
                            >${relatedProject.fullName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select name="country" class="browser-default custom-select">
                        <option value="">-- Выберите страну --</option>
                        <#list countries as country>
                            <option value="${country.id}"
                            >${country.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationMethod" class="browser-default custom-select">
                        <option value="">-- Выберите метод наблюдений --</option>
                        <#list observationMethods as observationMethod>
                            <option value="${observationMethod.id}"
                            >${observationMethod.name}</option>
                        </#list>
                    </select>
                </div>
            </div>

            <hr>
            <div class="discDiv">
                <div class="form-group row mt-3">
                    <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                    <div class="col-sm-9">
                        <select name="observationDiscipline" class="browser-default custom-select getObservationDiscipline">
                            <option value="0">-- Выберите дисциплину --</option>
                            <#list observationDisciplines as observationDiscipline>
                                <option value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="typeDiv">
                    <div class="form-group row mt-3">
                        <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                        <div class="col-sm-9">
                            <select name="observationType" class="browser-default custom-select getObservationType" disabled>
                                <option value="0">-- Выберите вид наблюдений --</option>
                            </select>
                        </div>
                    </div>
                    <div class="paramDiv">
                        <div class="form-group row mt-3">
                            <label class="col-sm-3 col-form-label">Параметр наблюдений:</label>
                            <div class="col-sm-9">
                                <select name="observationParameter" class="browser-default custom-select getObservationParameter" disabled>
                                    <option value="0">-- Выберите параметр наблюдений --</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row mt-3">
                        <a class="col-sm btn btn-primary addParam">Добавить параметр</a>
                    </div>
                </div>
                <div class="form-group row mt-3">
                    <a class="col-sm btn btn-primary addType">Добавить вид</a>
                </div>
            </div>
            <div class="form-group row mt-3">
                <a class="col-sm btn btn-primary addDisc">Добавить дисциплину</a>
            </div>
            <hr>

            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Сфера наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationScope" class="browser-default custom-select">
                        <option value="">-- Выберите сферу наблюдений --</option>
                        <#list observationScopes as observationScope>
                            <option value="${observationScope.id}"
                            >${observationScope.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Территория наблюдений:</label>
                <div class="col-sm-9">
                    <select name="geographicalObject" class="browser-default custom-select">
                        <option value="">-- Выберите территорию наблюдений --</option>
                        <#list geographicalObjects as geographicalObject>
                            <option value="${geographicalObject.id}"
                            >${geographicalObject.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <select name="organization" class="browser-default custom-select">
                        <option value="">-- Выберите организацию --</option>
                        <#list organizations as organization>
                            <option value="${organization.id}"
                            >${organization.fullName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <hr>
            <div class="form-group row mt-3">
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input  type="text" name="inventoryNumber" class="form-control" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Название CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="fullnameCdrom" class="form-control" placeholder="Введите полное название CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Аббревиатура CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="text" name="abbreviationCdrom" class="form-control" placeholder="Введите аббревиатуру CD-ROM">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" name="dateObservationStart" class="form-control" placeholder="Введите дату начала наблюдений дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>
                <div class="col-sm-9">
                    <input type="date" name="dateObservationEnd"  class="form-control" placeholder="Введите дату окончания наблюдений в формате дд.мм.гггг">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Объём данных:</label>
                <div class="col-sm-9">
                    <input type="text" name="volume" class="form-control" placeholder="Введите объём данных">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дата получения:</label>
                <div class="col-sm-9">
                    <input type="date" name="receivedDate" class="form-control" placeholder="Введите дату получения">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Краткое описание:</label>
                <div class="col-sm-9">
                    <textarea name="briefContent" class="form-control" rows="4" placeholder="Краткое описание информационного продукта"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дубликат:</label>
                <div class="col-sm-9">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate" value="1">
                        <label class="custom-control-label" for="customCheck1"></label>
                    </div>
                </div>
            </div>
            <div class="input-group" align="center">
                <div class="custom-file overflow-hidden">
                    <input type="file" multiple name="uploadFiles" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
                    <label class="custom-file-label" for="inputGroupFile04">Выберите файлы</label>
                </div>
            </div>
            <button type="submit" class="btn btn-indigo btn-block m-0 mt-4" value="save-information_resource">Сохранить</button><br/>
        </form>
        <a href="/information_resources" class="btn btn-blue-grey m-0 mb-3">Назад</a>
    </div>
    <#include "parts/scripts.ftl">
    <script>
        let disciplines =   '<#list observationDisciplines as observationDiscipline>' +
                                '<option value="${observationDiscipline.id}">${observationDiscipline.name}</option>' +
                            '</#list>';
    </script>
    <script type="text/javascript" src="/js/add_infres.js"></script>
<#include "parts/foot.ftl">