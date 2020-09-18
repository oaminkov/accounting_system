<#import "parts/viewtable.ftl" as v>
<@v.view "/add_observationdiscipline" "Дисциплины наблюдений" "Добавить дисциплину наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название дисциплины наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list listObservationDiscipline as observationDiscipline>
            <tr>
                <td>${observationDiscipline.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>