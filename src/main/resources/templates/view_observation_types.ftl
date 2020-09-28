<#import "parts/viewtable.ftl" as v>
<@v.view "/observation_types/add" "Виды наблюдений" "Добавить вид наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Вид наблюдений</th>
                <th>Дисциплина наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list listObservationType as observationType>
            <tr>
                <td>${observationType.name}</td>
                <td>${observationType.observationDiscipline.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>