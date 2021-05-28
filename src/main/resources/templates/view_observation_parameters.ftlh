<#import "parts/view_objects.ftlh" as v>
<@v.view "/observation_parameters/add" "Параметры наблюдений" "Добавить параметр наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Параметр наблюдений</th>
                <th>Дисциплина наблюдений</th>
                <th>Вид наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list observationParameters as observationParameter>
            <tr>
                <td>${observationParameter.name}</td>
                <td>${observationParameter.observationType.observationDiscipline.name}</td>
                <td>${observationParameter.observationType.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>