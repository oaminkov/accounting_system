<#include "parts/head.ftlh">
    <#include "parts/navbar.ftlh">
    <div class="container">
        <h2 class="mb-3" align="center">Создание нового мета-описания информационного ресурса</h2>

        <form action="/information_resources/add" enctype="multipart/form-data" method="post">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Тип ресурса:</label>
                <div class="col-sm-9">
                    <select name="resourceType" class="select2 form-control">
                        <option value="">-- Выберите тип ресурса --</option>
                        <#list resourceTypes as resourceType>
                            <option value="${resourceType.id}">${resourceType.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <hr>

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
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Язык представления:</label>
                <div class="col-sm-9">
                    <select name="language" class="select2 form-control">
                        <option value="">-- Выберите язык --</option>
                        <#list languages as language>
                            <option value="${language.id}">${language.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select name="country" class="select2 form-control">
                        <option value="">-- Выберите страну --</option>
                        <#list countries as country>
                            <option value="${country.id}">${country.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Главная организация:</label>
                <div class="col-sm-9 input-group">
                    <select name="mainOrganization" class="select2 form-control getOrganization">
                        <option value="0">-- Выберите организацию --</option>
                        <#list organizations as organization>
                            <option value="${organization.id}">${organization.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addOrganization">Добавить организацию</button>
            </div>
            <hr>

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
                <label class="col-sm-3 col-form-label">Дата получения CD-ROM:</label>
                <div class="col-sm-9">
                    <input type="date" name="receivedDate" class="form-control" placeholder="Введите дату получения">
                </div>
            </div>
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Связанный проект:</label>
                <div class="col-sm-9">
                    <select name="relatedProject" class="select2 form-control">
                        <option value="">-- Выберите связанный проект --</option>
                        <#list relatedProjects as relatedProject>
                            <option value="${relatedProject.id}">${relatedProject.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Метод наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationMethod" class="select2 form-control">
                        <option value="">-- Выберите метод наблюдений --</option>
                        <#list observationMethods as observationMethod>
                            <option value="${observationMethod.id}">${observationMethod.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <hr>

            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addDisc">Добавить дисциплину</button>
            </div>
            <hr>

            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addScope">Добавить сферу наблюдений</button>
            </div>
            <hr>

            <div class="form-group row m-0">
                <button type="button" class="btn btn-primary btn-block addTerritory">Добавить территорию наблюдений</button>
            </div>
            <hr>

            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Краткое описание содержания:</label>
                <div class="col-sm-9">
                    <textarea name="briefContent" class="form-control" rows="2" placeholder="Краткое описание информационного ресурса"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input required type="text" name="inventoryNumber" class="form-control" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дубликат:</label>
                <div class="col-sm-9">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="customCheck1" name="duplicate">
                        <label class="custom-control-label" for="customCheck1"></label>
                    </div>
                </div>
            </div>
            <hr>

            <div class="input-group" align="center">
                <div class="custom-file overflow-hidden">
                    <input type="file" multiple name="uploadFiles" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Прикрепить файлы</label>
                </div>
            </div>
            <hr>

            <button type="submit" class="btn btn-success btn-block m-0">Сохранить</button><br/>
        </form>
        <a href="/information_resources" class="btn btn-outline-secondary m-0 mb-4 waves-effect">Назад</a>
    </div>
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
    </script>
    <script type="text/javascript" src="/js/add_infres.js"></script>
<#include "parts/foot.ftlh">