<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div class="container">
        <form action="/search" method="post">
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>
                <div class="col-sm-9">
                    <select name="choiceObservationDiscipline" class="browser-default custom-select searchInput" id="getObservationDiscipline">
                        <option value="0">-- Выберите дисциплину наблюдений --</option>
                        <#list observationDisciplines as item>
                            <option value="${item.idObservationDiscipline}">${item.nameObservationDiscipline}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Вид наблюдений:</label>
                <div class="col-sm-9">
                    <select disabled name="choiceObservationType" class="browser-default custom-select searchInput" id="getObservationType">
                        <option value="0">-- Выберите вид наблюдений --</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Страна:</label>
                <div class="col-sm-9">
                    <select disabled name="choiceCountry" class="browser-default custom-select searchInput" id="getCountry">
                        <option value="0">-- Выберите страну --</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 col-form-label">Организация:</label>
                <div class="col-sm-9">
                    <select disabled name="choiceOrganization" class="browser-default custom-select searchInput" id="getOrganization">
                        <option value="0">-- Выберите организацию --</option>
                    </select>
                </div>
            </div>

<#--            <div class="form-group row">-->
<#--                <label class="col-sm-3 col-form-label">Дата начала наблюдений:</label>-->
<#--                <div class="col-sm-9">-->
<#--                    <input type="date" name="choiceDateObservationStart" class="form-control" placeholder="Введите дату начала наблюдений дд.мм.гггг">-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="form-group row">-->
<#--                <label class="col-sm-3 col-form-label">Дата окончания наблюдений:</label>-->
<#--                <div class="col-sm-9">-->
<#--                    <input type="date" name="choiceDateObservationEnd"  class="form-control" placeholder="Введите дату окончания наблюдений в формате дд.мм.гггг">-->
<#--                </div>-->
<#--            </div>-->
            <button disabled type="submit" class="btn btn-indigo btn-block m-0 mt-4" id="searchButton">Найти</button><br/>
        </form>
    </div>
    <#include "parts/scripts.ftl">
    <script type="text/javascript" src="/js/search.js"></script>
    <!--<script type="text/javascript" src="/static/js/search.js"></script>-->
<#include "parts/foot.ftl">