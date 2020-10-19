<#import "parts/view_table.ftl" as v>
<@v.view "/observation_methods/add" "Методы наблюдений" "Добавить метод наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название метода наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list observationMethods as observationMethod>
            <tr>
                <td>${observationMethod.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>