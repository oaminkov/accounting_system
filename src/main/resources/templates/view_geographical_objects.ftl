<#import "parts/view_table.ftl" as v>
<@v.view "/geographical_objects/add" "Территории наблюдений" "Добавить территорию наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название территории наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list geographicalObjects as geographicalObject>
            <tr>
                <td>${geographicalObject.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>