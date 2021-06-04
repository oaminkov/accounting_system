<#import "*/macros/common.ftlh" as c>
<@c.page>
    <main class="container">
        <h2 class="mb-3" align="center">Поиск информационных ресурсов</h2>

        <form action="/search" method="post">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Тип ресурса:</label>
                <div class="col-sm-9">
                    <select name="resourceType" class="select2 form-control">
                        <option value="0">-- Выберите тип ресурса --</option>
                        <#list resourceTypes as resourceType>
                            <option value="${resourceType.id}">${resourceType.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Язык представления:</label>
                <div class="col-sm-9">
                    <select name="language" class="select2 form-control">
                        <option value="0">-- Выберите язык --</option>
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
                        <option value="0">-- Выберите страну --</option>
                        <#list countries as country>
                            <option value="${country.id}">${country.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Главная организация:</label>
                <div class="col-sm-9">
                    <select name="mainOrganization" class="select2 form-control">
                        <option value="0">-- Выберите главную организацию --</option>
                        <#list organizations as organization>
                            <option value="${organization.id}">${organization.name}</option>
                        </#list>
                    </select>
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
                <label class="col-sm-3 col-form-label">Инвентарный номер:</label>
                <div class="col-sm-9">
                    <input type="text" name="inventoryNumber"  class="form-control" placeholder="Введите инвентарный номер">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationDiscipline" class="select2 form-control" id="getObservationDiscipline">
                        <option value="0">-- Выберите дисциплину наблюдений --</option>
                        <#list observationDisciplines as observationDiscipline>
                            <option value="${observationDiscipline.id}">${observationDiscipline.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                <div class="col-sm-9">
                    <select name="observationType" class="select2 form-control" id="getObservationType" disabled>
                        <option value="0">-- Выберите вид наблюдений --</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-block m-0 my-4" id="searchButton">Найти</button>
        </form>
    </main>

    <#include "*/parts/footer.ftlh">
    <#include "*/parts/scripts.ftlh">
    <script type="text/javascript" src="/js/change_types.js"></script>
</@c.page>