<#import "macros/view_objects.ftlh" as v>
<@v.view "/observation_territories/add" "Территории наблюдений" "Добавить территорию наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название территории наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list observationTerritories as observationTerritory>
            <tr>
                <td>${observationTerritory.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>