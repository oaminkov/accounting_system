<#import "parts/view_table.ftlh" as v>
<@v.view "/observation_disciplines/add" "Дисциплины наблюдений" "Добавить дисциплину наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название дисциплины наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list observationDisciplines as observationDiscipline>
            <tr>
                <td>${observationDiscipline.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>