<#import "parts/view_objects.ftlh" as v>
<@v.view "/observation_scopes/add" "Сферы наблюдений" "Добавить сферу наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Сфера наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list observationScopes as observationScope>
            <tr>
                <td>${observationScope.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>